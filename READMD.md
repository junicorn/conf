# conf

[![Build Status](https://img.shields.io/travis/junicorn/conf.svg?style=flat-square)](https://travis-ci.org/junicorn/conf)

这是一个解决Java开发中读取配置文件每次都要重写的困惑，待开发。

## 特性

* [x] 开箱即用,简单方便
* [x] 支持JDK1.6+
* [x] 无需过多依赖,按需添加

## 使用

添加Maven依赖：

```xml
<dependency>
	<groupId>com.junicorn</groupId>
	<artifactId>conf</artifactId>
	<version>0.0.1</version>
</dependency>
```

#### Properties 配置文件 

```java
Config config = Config.load("app.conf", PropertiesAdapter.class);

String name = config.get("name");

int age = config.getAsInt("age");
```

#### Xml 配置文件 

```java
...
```

#### Yaml 配置文件 

```java
...
```



