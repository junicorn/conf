package com.junicorn.conf.adapter;

import com.alibaba.fastjson.JSONObject;
import com.junicorn.conf.Config;
import com.junicorn.conf.exception.ConfigAdapterException;
import com.junicorn.conf.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Yuefei
 * @created 2018-06-28 11:02
 */
public class JsonAdapter extends ConfigAdapter {
    @Override
    public Config read(String prop_file) {
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(prop_file);
            StringBuffer sb = new StringBuffer();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len, "utf-8"));
            }
            this.configMap = (Map) JSONObject.parse(sb.toString());
        } catch (IOException e) {
            throw new ConfigAdapterException("load properties file error!");
        } finally {
            IOUtils.closeQuietly(in);
        }
        return this;
    }

    @Override
    public String toString() {
        return this.configMap.toString();
    }
}
