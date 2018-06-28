package com.junicorn.conf.util;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.beans.Introspector;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Liu Yuefei
 * @date Created in 2018/6/24 20:38
 * @description
 */
public class MapUtils {


    /**
     * @param map 需要转换的map
     * @param cls 目标javaBean的类对象
     * @return 目标类object
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<? extends T> cls) throws Exception {
        T object = cls.newInstance();
        for (String key : map.keySet()) {
            Field temFiels = cls.getDeclaredField(key);
            temFiels.setAccessible(true);
            Class<?> type = temFiels.getType();
            // 基本数据类型 或者 String
            boolean success = MapUtils.setBasicTypeValue(type, temFiels, object, map.get(key));
            if (success) {
                continue;
            }
            // 数组
            if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                List<Object> arrList = (List<Object>) map.get(key);
                if (isBasicType(componentType)) {
                    for (Object item : arrList) {
                        MapUtils.setBasicTypeValue(componentType, temFiels, object, item);
                    }
                } else {
                    // 对象类型数据
                    Object arr = Array.newInstance(componentType, arrList.size());
                    for (int i = 0; i < arrList.size(); i++) {
                        Map child = (Map) arrList.get(i);
                        Object item = mapToBean(child, componentType);
                        Array.set(arr, i, item);
                    }
                    temFiels.set(object, arr);
                }
            } else if (Map.class == type) {
                Map<String, Object> child = (Map<String, Object>) map.get(key);
                temFiels.set(object, child);
            } else if (List.class == type) {
                // TODO
                // 获取List中泛型的类型,需指定
                List<Object> srcList = (List<Object>) map.get(key);
                if (srcList == null || srcList.isEmpty()) {
                    temFiels.set(object, null);
                    continue;
                }
                String typeFieldName = temFiels.getName() + "Class";
                Field typeField = cls.getDeclaredField(typeFieldName);
                Class<?> templateType = typeField.getType();
                List<Object> targetList = new ArrayList<Object>();
                if (isBasicType(templateType)) {
                    Object value = null;
                    for (Object item : srcList) {
                        if (int.class == type || Integer.class == type) {
                            value = Integer.valueOf(item.toString());
                        } else if (String.class == type) {
                            value = item.toString();
                        } else if (boolean.class == type || Boolean.class == type) {
                            value = Boolean.valueOf(item.toString());
                        } else if (double.class == type || Double.class == type) {
                            value = Double.valueOf(item.toString());
                        } else if (long.class == type || Long.class == type) {
                            value = Long.valueOf(item.toString());
                        } else if (float.class == type || Float.class == type) {
                            value = Float.valueOf(item.toString());
                        } else if (char.class == type || Character.class == type) {
                            value = Character.valueOf(item.toString().charAt(0));
                        } else if (byte.class == type || Byte.class == type) {
                            value = Byte.valueOf(item.toString());
                        } else if (short.class == type || Short.class == type) {
                            value = Short.valueOf(item.toString());
                        }
                        targetList.add(value);
                    }
                } else {
                    for (Object item : srcList) {
                        Object itemValue = mapToBean((Map<String, Object>) item, templateType);
                        targetList.add(itemValue);
                    }
                }
                temFiels.set(object, targetList);
            } else {
                Map<String, Object> child = (Map<String, Object>) map.get(key);
                Object childObj = mapToBean(child, type);
                temFiels.set(object, childObj);
            }
        }
        return object;
    }


    private static boolean isBasicType(Class type) {
        if (int.class == type || Integer.class == type) {
            return true;
        } else if (String.class == type) {
            return true;
        } else if (boolean.class == type || Boolean.class == type) {
            return true;
        } else if (double.class == type || Double.class == type) {
            return true;
        } else if (long.class == type || Long.class == type) {
            return true;
        } else if (float.class == type || Float.class == type) {
            return true;
        } else if (char.class == type || Character.class == type) {
            return true;
        } else if (byte.class == type || Byte.class == type) {
            return true;
        } else if (short.class == type || Short.class == type) {
            return true;
        }
        return false;
    }


    private static boolean setBasicTypeValue(Class type, Field field, Object obj, Object value) throws IllegalAccessException {
        if (value == null) {
            field.set(obj, null);
            return true;
        }
        if (int.class == type || Integer.class == type) {
            field.set(obj, Integer.valueOf(value.toString()));
            return true;
        } else if (String.class == type) {
            field.set(obj, String.valueOf(value.toString()));
            return true;
        } else if (boolean.class == type || Boolean.class == type) {
            field.set(obj, Boolean.valueOf(value.toString()));
            return true;
        } else if (double.class == type || Double.class == type) {
            field.set(obj, Double.valueOf(value.toString()));
            return true;
        } else if (long.class == type || Long.class == type) {
            field.set(obj, Long.valueOf(value.toString()));
            return true;
        } else if (float.class == type || Float.class == type) {
            field.set(obj, Float.valueOf(value.toString()));
            return true;
        } else if (char.class == type || Character.class == type) {
            field.set(obj, Character.valueOf(value.toString().charAt(0)));
            return true;
        } else if (byte.class == type || Byte.class == type) {
            field.set(obj, Byte.valueOf(value.toString()));
            return true;
        } else if (short.class == type || Short.class == type) {
            field.set(obj, Short.valueOf(value.toString()));
            return true;
        }
        return false;
    }

}
