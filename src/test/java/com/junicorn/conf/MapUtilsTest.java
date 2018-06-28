package com.junicorn.conf;

import com.junicorn.conf.util.MapUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author  Liu Yuefei
 * @date   Created in 2018/6/24 21:09
 * @description
 */
public class MapUtilsTest {

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "zhangsan");
        map.put("age", "18");
        HashMap<String, Object> dog = new HashMap<String, Object>();
        dog.put("nickName", "wangcai");
        dog.put("age", null);
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(dog);
        map.put("dog", objects);
        Person person = MapUtils.mapToBean(map, Person.class);
        System.out.println(person);
    }

    public static class Person {
        private Integer age;
        private String name;
        private Dog[] dog;

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", dog=" + Arrays.toString(dog) +
                    '}';
        }
    }

    public static class Dog {
        private String nickName;
        private Integer age;

        @Override
        public String toString() {
            return "Dog{" +
                    "nickName='" + nickName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
