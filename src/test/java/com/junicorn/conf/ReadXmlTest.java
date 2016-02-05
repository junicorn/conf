package com.junicorn.conf;

import com.junicorn.conf.util.Xml;

public class ReadXmlTest {

	public static void main(String[] args) {
		Xml xml = new Xml("/config.xml");
		String name = xml.attrValue("//config/item[@key='name']");
		String version = xml.attrValue("//config/item[@key='version']");
		System.out.println(name);
		System.out.println(version);
	}
}
