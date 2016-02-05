# conf

[![Build Status](https://img.shields.io/travis/junicorn/conf.svg?style=flat-square)](https://travis-ci.org/junicorn/conf)

这是一个解决Java开发中读取配置文件每次都要重写的困惑。

## 特性

* [x] 开箱即用,简单方便
* [x] 支持JDK1.6+
* [x] 无需过多依赖,按需添加

## 状态

- [已完成] 解析Properties配置文件
- [待完成] 解析Xml配置文件
- [待完成] 解析Ini配置文件
- [待完成] 解析Yaml配置文件

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
Config config = ConfigLoader.load("appconf.properties");
		
String name = config.getString("name");
System.out.println("name = " + name);

AppConf appConf = config.get(AppConf.class);
System.out.println(appConf.name());
System.out.println(appConf.age());
```

#### Xml 配置文件 

```java
Config config = ConfigLoader.load("app.conf");
```

#### Ini 配置文件 

```java
Config config = ConfigLoader.load("app.conf");
```

#### Yaml 配置文件 

```java
...
```

#### 解析配置文件到接口

```java
Config config = ConfigLoader.load("app.conf");
AppConf appconf = config.get(AppConf.class);
```

```java
public interface AppConf {
	String name();
	int age();
}
```

#### 自定义配置文件

`待规划`

