package com.junicorn.conf;

public interface Config {

	String getString(String key);
	
	Integer getInt(String key);
	
	Long getLong(String key);
	
	Boolean getBool(String key);
	
	Double getDouble(String key);
	
	<T> T get(Class<T> t);
}