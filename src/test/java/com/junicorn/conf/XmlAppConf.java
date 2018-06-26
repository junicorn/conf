package com.junicorn.conf;

import java.util.Arrays;

/**
 * @author Liu Yuefei
 * @description
 * @date Created in 10:57 2018-06-26
 */
public class XmlAppConf {
    private KeyValue[] item;

    @Override
    public String toString() {
        return "XmlAppConf{" +
                "item=" + Arrays.toString(item) +
                '}';
    }

    public KeyValue[] getItem() {
        return item;
    }

    public void setItem(KeyValue[] item) {
        this.item = item;
    }

    public static class KeyValue {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "KeyValue{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
