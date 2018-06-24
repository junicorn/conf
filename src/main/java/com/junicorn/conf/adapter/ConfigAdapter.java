package com.junicorn.conf.adapter;

import com.junicorn.conf.Config;
import com.junicorn.conf.util.MapUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
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
    public <T> List<T> getList(String key) {
        return null;
    }

    @Override
    public Config getConfig(String key) {
        return null;
    }


    public <T> T get(Class<T> t) {
        try {
            // 改用反射为对象赋值
            return MapUtils.mapToBean(this.configMap, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("convert to " + t.getName() + " error!");
        }
        /*try {
            @SuppressWarnings("unchecked")
            T tobj = (T) Proxy.newProxyInstance(t.getClassLoader(),
                    new Class[]{t}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                            String method_name = method.getName();
                            Class<?> returnClazz = method.getReturnType();

                            if (returnClazz == String.class) {
                                return ConfigAdapter.this.getString(method_name);
                            } else if (returnClazz == Integer.class || returnClazz == int.class) {
                                return ConfigAdapter.this.getInt(method_name);
                            } else if (returnClazz == Long.class || returnClazz == long.class) {
                                return ConfigAdapter.this.getLong(method_name);
                            } else if (returnClazz == Double.class || returnClazz == double.class) {
                                return ConfigAdapter.this.getDouble(method_name);
                            } else if (returnClazz == Boolean.class || returnClazz == boolean.class) {
                                return ConfigAdapter.this.getBoolean(method_name);
                            } else if (returnClazz == List.class) {
                                return ConfigAdapter.this.getList(method_name);
                            }
                            return null;
                        }
                    });
            return tobj;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;*/
    }


    public abstract Config read(String conf);

}
