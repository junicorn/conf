package com.junicorn.conf.util;

import com.junicorn.conf.Config;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author  Liu Yuefei
 * @date   Created in 2018/6/24 11:37
 * @description    采用sax解析xml
 */
public class XmlParserUtils extends DefaultHandler {

    private Xml result;

    private SAXParser parser;

    private InputSource inputSource;

    public XmlParserUtils(InputStream xmlInputStream) {
        this.inputSource = new InputSource(xmlInputStream);
    }

    private void parser() throws IOException, ParserConfigurationException, SAXException {
        this.parser = SAXParserFactory.newInstance().newSAXParser();
        this.parser.parse(inputSource, this);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        result.addContent(new String(ch, start, length).trim());
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
        if (result == null) {
            result = new Xml();
        } else {
            Xml childTag = new Xml();
            childTag.setParent(result);
            result.addChild(childTag);
            result = childTag;
        }
        result.setName(qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            String attrName = attributes.getQName(i);
            result.addAttribute(attrName, attributes.getValue(attrName));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (result.getParent() != null) {
            result = result.getParent();
        }
    }


    public Xml getResult() throws ParserConfigurationException, SAXException, IOException {
        this.parser();
        return result;
    }

    /***
     * 解析后存储数据的javaBean
     */
    public static class Xml {

        private String name;

        private Xml parent;

        private Map<String, String> attributes;

        private String text;

        private List<Xml> children;


        public String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }


        public List<Xml> getChildren() {
            return children;
        }


        public String getText() {
            return this.text;
        }


        void addContent(String text) {
            if (this.text == null) {
                this.text = text;
            } else {
                this.text += text;
            }
        }


        public Xml getParent() {
            return parent;
        }

        void setParent(Xml parent) {
            this.parent = parent;
        }

        void addChild(Xml child) {
            if (children == null) {
                children = new ArrayList<Xml>();
            }
            children.add(child);
        }

        void addAttribute(String key, String value) {
            if (attributes == null) {
                attributes = new HashMap<String, String>();
            }
            attributes.put(key, value);
        }

        @Override
        public String toString() {
            return "{" +
                    "\"name\":" + "\"" + name + "\"" +
                    (attributes == null ? "" : (", \"attributes\":" + attributes.toString().replaceAll("=", "\":\"")
                            .replaceAll("\\{", "{\"")
                            .replaceAll(",", "\",\"")
                            .replaceAll("}", "\"}"))) +
                    ((text == null || text.isEmpty()) ? "" : (",\"text\":" + "\"" + text + '\"')) +
                    (children == null ? "" : (", \"children\":" + children)) +
                    '}';
        }

    }
}
