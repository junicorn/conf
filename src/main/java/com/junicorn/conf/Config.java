package com.junicorn.conf;

public interface Config {

	String get(String key);
	
	Integer getAsInt(String key);
	
	Long getAsLong(String key);
	
	Boolean getAsBool(String key);
	
	Double getAsDouble(String key);
	
}