package com.junicorn.conf.adapter;

import com.junicorn.conf.Config;
import com.junicorn.conf.util.MapUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 配置适配器，用于适配各种配置文件，解析成Config接口
 * 实现类需要完成的就是向configMap内赋值
 */
public abstract class ConfigAdapter implements Config {

    /**
     * 存储配置数据
     */
    protected Map<String, Object> configMap = new HashMap<String, Object>();

    public String getString(String key) {
        Object object = configMap.get(key);
        if (null != object) {
            return object.toString();
        }
        return null;
    }

    public Integer getInt(String key) {
        String value = this.getString(key);
        if (null != value) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public Long getLong(String key) {
        String value = this.getString(key);
        if (null != value) {
            return Long.parseLong(value);
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        String value = this.getString(key);
        if (null != value) {
            return Boolean.parseBoolean(value);
        }
        return null;
    }

    public Double getDouble(String key) {
        String value = this.getString(key);
        if (null != value) {
            return Double.parseDouble(value);
        }
        return null;
    }

    @Override
    public List<Config> getList(String key) {
        List<Config> retList = new LinkedList<Config>();
        if (this.configMap.get(key) instanceof List) {
            List<Map> list = (List<Map>) this.configMap.get(key);
            for (int i = 0; i < list.size(); i++) {
                try {
                    ConfigAdapter child = this.getClass().newInstance();
                    child.configMap = list.get(i);
                    retList.add(child);
                } catch (Exception e) {
                    return null;
                }
            }
            return retList;
        }
        return null;
    }

    @Override
    public Config getConfig(String key) {
        if (this.configMap.get(key) instanceof Map) {
            try {
                ConfigAdapter child = this.getClass().newInstance();
                child.configMap = (Map<String, Object>) this.configMap.get(key);
                return child;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public <T> T get(Class<T> t) {
        try {
            // 改用反射为对象赋值
            return MapUtils.mapToBean(this.configMap, t);
            /*T obj = t.newInstance();
            BeanUtils.populate(obj, this.configMap);
            return obj;*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("convert to " + t.getName() + " error!");
        }
    }


    public abstract Config read(String conf);

}
