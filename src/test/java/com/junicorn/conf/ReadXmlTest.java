package com.junicorn.conf;

import com.junicorn.conf.adapter.ConfigAdapter;
import com.junicorn.conf.adapter.XmlAdapter;
import com.junicorn.conf.loader.ConfigLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ReadXmlTest {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, InvocationTargetException, IllegalAccessException {
        ConfigAdapter config = (ConfigAdapter) ConfigLoader.load("config.xml", XmlAdapter.class).getConfig("dependencies");
        XmlConf xmlConf = config.get(XmlConf.class);
        for (XmlConf.Depend depend : xmlConf.getDependency()) {
            System.out.println(depend.toString());
        }
        System.out.println(xmlConf);

    }
}
