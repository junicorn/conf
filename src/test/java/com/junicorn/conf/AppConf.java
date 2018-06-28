package com.junicorn.conf;

public class AppConf {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AppConf{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
