package com.junicorn.conf.adapter;

import java.util.HashMap;
import java.util.Map;

import com.junicorn.conf.Config;

/**
 * 配置适配器，用于适配各种配置文件，解析成Config接口 
 */
public abstract class ConfigAdapter implements Config {

	protected Map<String, Object> configMap = new HashMap<String, Object>();
	
	public String getString(String key) {
		Object object = configMap.get(key);
		if(null != object){
			return object.toString();
		}
		return null;
	}

	public Integer getInt(String key) {
		String value = this.getString(key);
		if(null != value){
			return Integer.parseInt(value);
		}
		return null;
	}

	public Long getLong(String key) {
		String value = this.getString(key);
		if(null != value){
			return Long.parseLong(value);
		}
		return null;
	}

	public Boolean getBoolean(String key) {
		String value = this.getString(key);
		if(null != value){
			return Boolean.parseBoolean(value);
		}
		return null;
	}

	public Double getDouble(String key) {
		String value = this.getString(key);
		if(null != value){
			return Double.parseDouble(value);
		}
		return null;
	}
	
	public abstract Config read(String conf);
	
	
}
