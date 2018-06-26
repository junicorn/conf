package com.junicorn.conf.adapter;

import com.junicorn.conf.Config;
import com.junicorn.conf.exception.ConfigAdapterException;
import com.junicorn.conf.exception.LoadException;
import com.junicorn.conf.util.XmlParserUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 解析Xml配置文件
 */
public class XmlAdapter extends ConfigAdapter {


    @Override
    public Config read(String prop_file) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(prop_file);

        try {
            XmlParserUtils parser = new XmlParserUtils(in);
            // 解析xml文件
            XmlParserUtils.Xml result = parser.getResult();
            // 转为map存储
            this.configMap = this.convertToMap(result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            throw new ConfigAdapterException("create parser error!");
        } catch (IOException e) {
            throw new LoadException("load xml file error!");
        }
        return this;
    }

    /***
     * xml对象转换为Map
     * @param xml
     * @return
     */
    public Map<String, Object> convertToMap(XmlParserUtils.Xml xml) {
        Map<String, Object> self = new HashMap<String, Object>();

        if (onlyOwnText(xml)) {
            self.put(xml.getName(), xml.getText());
            return self;
        }
        Map<String, Object> attrMap = new HashMap<String, Object>();
        if (xml.getText() != null && !xml.getText().trim().isEmpty()) {
            attrMap.put("text", xml.getText());
        }
        if (xml.getAttributes() != null) {
            attrMap.putAll(xml.getAttributes());
        }
        List<XmlParserUtils.Xml> children = xml.getChildren();
        if (children != null) {
            for (XmlParserUtils.Xml item : children) {
                Map<String, Object> childMap = convertToMap(item);
                String childName = childMap.keySet().iterator().next();
                if (attrMap.containsKey(childName)) {
                    if (attrMap.get(childName) instanceof List) {
                        List<Object> list = (List<Object>) attrMap.get(childName);
                        list.add(childMap.get(childName));
                    } else {
                        Object remove = attrMap.remove(childName);
                        List<Object> list = new LinkedList<Object>();
                        list.add(remove);
                        list.add(childMap.get(childName));
                        attrMap.put(childName, list);
                    }
                } else {
                    attrMap.put(childName, childMap.get(childName));
                }
            }
        }
        self.put(xml.getName(), attrMap);
        return self;
    }

    private boolean onlyOwnText(XmlParserUtils.Xml xml) {
        if (xml.getAttributes() == null && (xml.getChildren() == null || xml.getChildren().isEmpty())) {
            return true;
        }
        return false;
    }

    private boolean onlyOwnChildren(XmlParserUtils.Xml xml) {
        if (xml.getAttributes() != null) {
            return false;
        }
        if (xml.getText() != null && !xml.getText().isEmpty()) {
            return false;
        }
        return true;
    }


    @Override
    public List<Config> getList(String key) {
        List<Config> retList = new LinkedList<Config>();
        if (this.configMap.get(key) instanceof List) {
            List<Map> list = (List<Map>) this.configMap.get(key);
            for (int i = 0; i < list.size(); i++) {
                XmlAdapter child = new XmlAdapter();
                child.configMap = list.get(i);
                retList.add(child);
            }
            return retList;
        }
        return null;
    }

    @Override
    public Config getConfig(String key) {
        if (this.configMap.get(key) instanceof Map) {
            XmlAdapter child = new XmlAdapter();
            child.configMap = (Map<String, Object>) this.configMap.get(key);
            return child;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.configMap.toString();
    }
}