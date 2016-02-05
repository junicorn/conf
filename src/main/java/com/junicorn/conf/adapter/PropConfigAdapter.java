package com.junicorn.conf.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.junicorn.conf.Config;
import com.junicorn.conf.exception.ConfigAdapterException;
import com.junicorn.conf.util.IOUtils;

public class PropConfigAdapter extends ConfigAdapter {
	
	@Override
	public Config read(String prop_file) {
		
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(prop_file);
			if (in != null) {
				props.load(in);
			}
			// 解析properties文件
			Set<Entry<Object, Object>> set = props.entrySet();
			Iterator<Map.Entry<Object, Object>> it = set.iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				String fuKey = getWildcard(value);
				if(null != fuKey && null != props.get(fuKey)){
					String fuValue = props.get(fuKey).toString();
					value = value.replaceAll("\\$\\{" + fuKey + "\\}", fuValue);
				}
				configMap.put(key, value);
			}
			return this;
		} catch (IOException e) {
			throw new ConfigAdapterException("load properties file error!");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	private String getWildcard(String str){
		if(null != str && str.indexOf("${") != -1){
			int start = str.indexOf("${");
			int end = str.indexOf("}");
			if(start != -1 && end != -1){
				return str.substring(start + 2, end);
			}
		}
		return null;
	}
	
	@Override
	public <T> T get(Class<T> t) {
		try {
			@SuppressWarnings("unchecked")
			T tobj = (T) Proxy.newProxyInstance(t.getClassLoader(), 
					new Class[] { t }, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							
							String method_name = method.getName();
							Class<?> returnClazz = method.getReturnType();
							
							if(returnClazz == String.class){
								return PropConfigAdapter.this.getString(method_name);
							}
							
							if (returnClazz == Integer.class || returnClazz == int.class) {
								return PropConfigAdapter.this.getInt(method_name);
							}
							
							if(returnClazz == Long.class || returnClazz == long.class){
								return PropConfigAdapter.this.getLong(method_name);
							}
							
							if(returnClazz == Double.class || returnClazz == double.class){
								return PropConfigAdapter.this.getDouble(method_name);
							}
							
							if(returnClazz == Boolean.class || returnClazz == boolean.class){
								return PropConfigAdapter.this.getBoolean(method_name);
							}
							
							return null;
						}
					});
			return tobj;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}