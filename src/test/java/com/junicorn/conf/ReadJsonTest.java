package com.junicorn.conf;

import com.junicorn.conf.adapter.JsonAdapter;
import com.junicorn.conf.loader.ConfigLoader;

import java.io.IOException;

/**
 * @author Liu Yuefei
 * @created 2018-06-28 11:19
 */
public class ReadJsonTest {

    public static void main(String[] args) throws IOException {
        Config config = ConfigLoader.load("config.json", JsonAdapter.class).getConfig("dependencies");
        XmlConf xmlConf = config.get(XmlConf.class);
        System.out.println(xmlConf.getDependency().get(0).getGroupId());
    }
}
