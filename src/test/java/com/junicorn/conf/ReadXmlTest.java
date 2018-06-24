package com.junicorn.conf;

import com.junicorn.conf.adapter.XmlAdapter;
import com.junicorn.conf.loader.ConfigLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ReadXmlTest {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Config config = ConfigLoader.load("config.xml", XmlAdapter.class).getConfig("dependencies");
        System.out.println(config);
        XmlConf conf = config.get(XmlConf.class);
        System.out.println(conf);

    }
}
