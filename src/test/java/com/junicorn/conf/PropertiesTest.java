package com.junicorn.conf;

import com.junicorn.conf.adapter.PropConfigAdapter;
import com.junicorn.conf.loader.ConfigLoader;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/6/24 10:04
 * @Description:
 */
public class PropertiesTest {

    public static void main(String[] args) {
        Config config = ConfigLoader.load("appconf.properties", PropConfigAdapter.class);
        AppConf conf = config.get(AppConf.class);
        System.out.println(conf);
    }
}
