package com.junicorn.conf;

import com.junicorn.conf.loader.ConfigLoader;

public class TestMain {

	public static void main(String[] args) {
		Config config = ConfigLoader.load("appconf.properties");
		
		String name = config.getString("name");
		System.out.println("name = " + name);
		
		AppConf appConf = config.get(AppConf.class);
		System.out.println(appConf.name());
		System.out.println(appConf.age());
	}
	
}
