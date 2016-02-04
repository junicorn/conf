package com.junicorn.conf.loader;

import java.io.InputStream;

import com.junicorn.conf.Config;
import com.junicorn.conf.adapter.ConfigAdapter;
import com.junicorn.conf.adapter.PropConfigAdapter;
import com.junicorn.conf.exception.LoadException;
import com.junicorn.conf.util.ClassUtils;

public class ConfigLoader {
	
	public static Config load(String conf){
		return load(conf, PropConfigAdapter.class);
	}
	
	public static Config load(String conf, Class<PropConfigAdapter> adapter){
		
		if(null == conf || conf.equals("")){
			throw new LoadException("the config file name is null");
		}
		
		if(null == adapter){
			throw new LoadException("the config adapter class is null");
		}
		
		try {
			InputStream ins = ConfigLoader.class.getResourceAsStream(conf);
			ConfigAdapter configAdapter = (ConfigAdapter) ClassUtils.newInstance(adapter);
			return configAdapter.read(ins);
		} catch (Exception e) {
			throw new LoadException(e);
		}
	}
}
