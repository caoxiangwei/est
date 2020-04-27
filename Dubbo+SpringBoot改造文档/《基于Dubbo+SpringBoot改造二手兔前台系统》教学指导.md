# 《基于Dubbo+SpringBoot改造二手兔前台系统》教学指导

# 1 本章设计

![](教学指导资料\image\PPT结构设计.png)

<center>图 PPT详细设计</center>

## 1.1 重难点分析

在互联网软件行业，面对海量用户的高并发请求，常规的垂直应用架构已无法应对，分布式微服务架构势在必行，所以要求学生掌握微服务架构相关技术，本章课程要求学生**重点掌握基于Dubbo改造二手兔前台系统**。

## 1.2 实施目标

掌握基于Dubbo改造二手兔前台系统。

## 1.3 本章设计思路

设计思路为：

> 业务分析  →  业务具体实现  →  开发管理

*以下的教学指导内容会和PPT进行对应（以PPT标题为连接点），如果没有对应的PPT标题内容，说明此页PPT较为简单无教学指导*。

# 2 基于Dubbo+SpringBoot改造二手兔前台系统

## 2.1 基于Dubbo+SpringBoot改造二手兔前台系统分析

### 2.1.1 改造前项目结构分析

![](教学指导资料\image\改造前项目结构图.jpg)

<center>图 改造前项目结构图</center>
![](\教学指导资料\image\项目结构图.png)

<center>图 改造前项目结构功能图</center>
二手兔项目改造前分为四个子Moudle，分别是：est-model、est-dao、est-service、est-web

```
每个Moudle功能如下：
est-model：提供项目统一要使用的和数据库表对应的Molel实体类
est-dao：对接MySQL数据库，提供项目统一要使用的Mapper类
est-service：调用Dao模块的Mapper接口，进行业务处理
est-web：调用Service层的业务处理接口，进行数据输入输出控制
```

### 2.1.2 改造后项目分析

![](\教学指导资料\image\改造后项目结构图.jpg)

<center>图 改造后项目结构图</center>
二手兔项目改造后分为三个子Moudle，分别是est-common、est-provider、est-consumer；其中est-common模块下面还分为est-dao、est-model、est-service三个子Moudle。

```
每个Moudle功能如下：
est-common：存放接口和公共代码部分
est-provider：接口的具体实现，暴露服务的服务提供方
est-consumer：调用远程服务的服务消费方
```

## 2.2 基于Dubbo+SpringBoot改造二手兔前台系统实现

### 2.2.1 基于Maven搭建项目基础框架

参考《**基于Maven搭建二手兔改造后项目基础框架**》

### 2.2.2 基于Dubbo改造est-common项目

1、将est-dao、est-model、est-service的代码依次拷贝到改造后项目的目录中，如下所示：

![](教学指导资料\image\est-dao.jpg)

<center>图 est-dao</center>
![](教学指导资料\image\est-model.jpg)

<center>图 est-model</center>
![](教学指导资料\image\est-service.jpg)

<center>图 est-service</center>
2、修改pom.xml配置文件

est-dao的pom.xml配置文件如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>cn.est</groupId>
        <artifactId>est-common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <artifactId>est-dao</artifactId>

    <dependencies>
        <!--Other Moudle-->
        <dependency>
            <groupId>cn.est</groupId>
            <artifactId>est-model</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>
    </dependencies>

</project>
```

est-model的pom.xml配置文件如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>cn.est</groupId>
        <artifactId>est-common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>est-model</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!-- swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!-- swagger默认依赖了1.5.0，我们指定一个高版本避免 swagger报错Illegal DefaultValue  for parameter type integer -->
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
            <version>1.5.22</version>
        </dependency>

    </dependencies>

</project>
```

est-service的pom.xml配置文件如下：

```xml
![6-1](D:\WorkSpace\vss\10-基于Dubbo+SpringBoot改造二手兔前台系统\01-教学PPT\教学指导资料\image\6-1.jpg)<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>cn.est</groupId>
        <artifactId>est-common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>est-service</artifactId>

    <properties>
        <spring.boot.version>2.1.7.RELEASE</spring.boot.version>
    </properties>

    <dependencies>
        <!--Other Moudle-->
        <dependency>
            <groupId>cn.est</groupId>
            <artifactId>est-model</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>


        <dependency>
            <groupId>com.alipay.sdk</groupId>
            <artifactId>alipay-sdk-java</artifactId>
            <version>3.1.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-core</artifactId>
            <version>9.0.22</version>
        </dependency>
    </dependencies>

</project>
```

### 2.2.3 基于Dubbo改造est-provider项目

参考《**基于Dubbo改造est-provider项目**》

### 2.2.4 基于Dubbo改造est-consumer项目

参考《**基于Dubbo改造est-consumer项目**》

## 2.3 开发管理

开发管理为每一章的PPT都通用的内容，该部分内容作用如下

- 对当前项目进度进行梳理
- 对班级学员进度进行抽查
- 布置开发任务及预习作业

> **Doing中为当日应该完成的工作，Done中的内容是已经完成的工作**

| **Todo**     | **Doing**                           | **Done**                             |
| ------------ | ----------------------------------- | ------------------------------------ |
| **项目总结** | **基于Dubbo+Spring Boot改造二手兔** | **1.微服务架构**<br/>**2.Dubbo技术** |

**注**：**今日课程结束后，学员的能力进阶为120分。能力进阶部分不要轻易略过，须重点强调！旨在鼓励学员坚持下去

