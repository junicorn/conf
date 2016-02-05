package com.junicorn.conf.exception;

public class ConfigAdapterException extends RuntimeException {

	private static final long serialVersionUID = 6118361499832602075L;
	
	public ConfigAdapterException(String msg){
		super(msg);
	}
	
	public ConfigAdapterException(Throwable t){
		super(t);
	}
	
}
