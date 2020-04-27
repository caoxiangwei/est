# 基于Maven搭建二手兔改造后项目基础框架

## 1.项目架构设计

![二手兔项目改造后结构图](image\改造后项目结构图.jpg)

<center>图 二手兔改造后项目结构图</center>
## 2.创建父项目项目

按照以下步骤创建est项目

![1](image\1-1.jpg)

![2](image\1-2.jpg)

![3](image\1-3.jpg)

修改est项目的pom.xml配置文件，修改如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<packaging>pom</packaging>
	<groupId>cn.est</groupId>
	<artifactId>est</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>est</name>
	<description>est</description>

	<properties>
		<java.version>1.8</java.version>
		<spring.boot.version>2.1.7.RELEASE</spring.boot.version>
		<dubbo.version>2.7.3</dubbo.version>
		<slf4j.version>1.7.28</slf4j.version>
		<maven.compiler.plugin.version>3.8.1</maven.compiler.plugin.version>
		<file.encoding>UTF-8</file.encoding>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencyManagement>
		<dependencies>
			<!--Dubbo-->
			<dependency>
				<groupId>org.apache.dubbo</groupId>
				<artifactId>dubbo-spring-boot-starter</artifactId>
				<version>${dubbo.version}</version>
			</dependency>
			<dependency>
				<groupId>org.apache.dubbo</groupId>
				<artifactId>dubbo</artifactId>
				<version>${dubbo.version}</version>
			</dependency>
			<dependency>
				<groupId>org.apache.dubbo</groupId>
				<artifactId>dubbo-dependencies-zookeeper</artifactId>
				<version>${dubbo.version}</version>
				<type>pom</type>
			</dependency>

            <!--slf4j-->
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>log4j-over-slf4j</artifactId>
                <version>${slf4j.version}</version>
            </dependency>

			<!--Springboot-->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>${spring.boot.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<!-- 如果不添加此节点mybatis的mapper.xml文件都会被漏掉。 -->
		<resources>
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>**/*.yml</include>
					<include>**/*.properties</include>
					<include>**/*.xml</include>
				</includes>
				<filtering>false</filtering>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
				<includes>
					<include>**/*.yml</include>
					<include>**/*.properties</include>
					<include>**/*.xml</include>
					<!--<include>**/*.html</include>-->
				</includes>
				<filtering>false</filtering>
			</resource>
		</resources>
		<finalName>est</finalName>
	</build>

	<!-- 模块说明：这里声明多个子模块 -->
	<modules>
		<module>est-common</module>
		<module>est-provider</module>
		<module>est-consumer</module>
	</modules>

</project>
```

## 3.创建普通子项目

按照以下步骤创建est-model、est-dao、est-service子项目

![1](image\2-1.jpg)

![2](image\1-1.jpg)

![3](image\2-2.jpg)

![4](image\2-3.jpg)

各子项目的pom文件如下

est-common的pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>cn.est</groupId>
    <artifactId>est-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>est-common</name>
    <packaging>pom</packaging>

    <dependencies>
    </dependencies>

    <modules>
        <module>est-service</module>
        <module>est-dao</module>
        <module>est-model</module>
    </modules>
</project>
```

est-dao的pom文件

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

est-model的pom文件

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

est-service的pom文件

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

## 4.创建Provider项目

按照以下步骤创建est-provider项目

![1](image\3-1.jpg)

![2](image\3-2.jpg)

![3](image\3-3.jpg)

![4](image\3-4.jpg)

est-provider的pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>est</artifactId>
        <groupId>cn.est</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>est-provider</artifactId>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <encoding>UTF-8</encoding>
    </properties>

    <dependencies>
        <!--Other Moudle-->
        <dependency>
            <groupId>cn.est</groupId>
            <artifactId>est-service</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>cn.est</groupId>
            <artifactId>est-dao</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!--SpringBoot-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--Dubbo-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <!-- slf4j logback -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>log4j-over-slf4j</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <type>pom</type>
        </dependency>

        <!--redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!--aliyun-->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-core</artifactId>
            <version>4.1.0</version>
        </dependency>
        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.31</version>
        </dependency>
        <!--oss-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>3.5.0</version>
        </dependency>
        <!--tomcat-->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-core</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <!-- 如果不添加此节点mybatis的mapper.xml文件都会被漏掉。 -->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>

        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

## 5.创建Consumer项目

创建est-consumer项目步骤参考est-provider项目即可，est-consumer的pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>est</artifactId>
        <groupId>cn.est</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>est-consumer</artifactId>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <encoding>UTF-8</encoding>
    </properties>

    <dependencies>
        <!--Other Moudle-->
        <dependency>
            <groupId>cn.est</groupId>
            <artifactId>est-service</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--Dubbo-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <!-- slf4j logback -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>log4j-over-slf4j</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <type>pom</type>
        </dependency>

        <!--redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.31</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```