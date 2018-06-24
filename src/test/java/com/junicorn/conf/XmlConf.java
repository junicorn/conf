package com.junicorn.conf;

import java.util.List;
import java.util.Map;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/6/24 21:30
 * @Description:
 */
public class XmlConf {

    private Depend[] dependency;


    public static class Depend {
        private Map<String, Object> groupId;
        private Map<String, Object> artifactId;
        private Map<String, Object> version;
        private Map<String, Object> scope;

    }

}
