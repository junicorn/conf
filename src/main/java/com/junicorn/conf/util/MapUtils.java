package com.junicorn.conf.util;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/6/24 20:38
 * @Description:
 */
public class MapUtils {


    /**
     * @param map 需要转换的map
     * @param cls 目标javaBean的类对象
     * @return 目标类object
     * @throws Exception
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<? extends T> cls) throws Exception {
        T object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            Class<?> type = temFiels.getType();
            if (int.class == type || Integer.class == type) {
                temFiels.set(object, Integer.valueOf(map.get(key).toString()));
            } else if (String.class == type) {
                temFiels.set(object, map.get(key).toString());
            } else if (boolean.class == type || Boolean.class == type) {
                temFiels.set(object, Boolean.valueOf(map.get(key).toString()));
            } else if (double.class == type || Double.class == type) {
                temFiels.set(object, Double.valueOf(map.get(key).toString()));
            } else if (long.class == type || Long.class == type) {
                temFiels.set(object, Long.valueOf(map.get(key).toString()));
            } else if (float.class == type || Float.class == type) {
                temFiels.set(object, Double.valueOf(map.get(key).toString()));
            } else if (char.class == type || Character.class == type) {
                temFiels.set(object, Character.valueOf(map.get(key).toString().charAt(0)));
            } else if (byte.class == type || Byte.class == type) {
                temFiels.set(object, Byte.valueOf(map.get(key).toString()));
            } else if (short.class == type || Short.class == type) {
                temFiels.set(object, Short.valueOf(map.get(key).toString()));
            } else if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                List<Map> arrList = (List<Map>) map.get(key);
                Object arr = Array.newInstance(componentType, arrList.size());
                for (int i = 0; i < arrList.size(); i++) {
                    Map child = arrList.get(i);
                    Object item = mapToBean(child, componentType);
                    Array.set(arr, i, item);
                }
                temFiels.set(object, arr);
            } else if (Map.class == type) {
                Map<String, Object> child = (Map<String, Object>) map.get(key);
                temFiels.set(object, child);
            } else if (List.class != type || Map.class != type) {
                Map<String, Object> child = (Map<String, Object>) map.get(key);
                Object childObj = mapToBean(child, type);
                temFiels.set(object, childObj);
            } else {
                temFiels.set(object, map.get(key));
            }
        }
        return object;
    }
}
