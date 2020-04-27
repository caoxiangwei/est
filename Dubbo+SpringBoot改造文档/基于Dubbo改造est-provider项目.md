# 基于Dubbo改造est-provider项目

## 1.项目目录结构

![二手兔项目改造后结构图](image\provider.jpg)

<center>图 est-provider项目目录结构图</center>
## 2.基于Dubbo改造est-provider项目

1、est-provider项目的pom.xml文件中添加依赖，如下所示：

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

2、修改配置文件

application.yml配置文件如下所示：

```yml
spring:
  profiles:
    active: dev

#mybatis
mybatis:
  mapper-locations: classpath:mapper/*.xml

server:
  port: 8088
```

application-dev.yml配置文件如下所示：

```yml
spring:
  #数据库
  datasource:
    username: root
    password: fanlixia520!!
    url: jdbc:mysql://127.0.0.1/est?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8
    driver-class-name: com.mysql.cj.jdbc.Driver

  # Redis
  redis:
    host: 127.0.0.1
    port: 6379
    password: redis123456
    #连接0库
    database: 0
    #连接超时时间（毫秒）
    timeout: 3000


dubbo:
  application:            #应用配置，用于配置当前应用信息
    name: dubbo_provider
  registry:                 #注册中心配置
    address: zookeeper://127.0.0.1:2181
  metadata-report:
    address: zookeeper://127.0.0.1:2181
  protocol:     #协议配置，协议由提供方指定，消费方被动接受，尽量多在服务方配置
    name: dubbo
    port: 20888


#配置支付宝支付参数
alipay:
  appID: 2016100200643488
  privateKey: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5pJaMzr16W6T8Nu/xZorDcL5rcpcTgzDeBU2Z3ozs6LsaJ4Frwt2xDTMYlM8Ob3va8DmJf57VQLB6jKlFZ02bXgGwAXFrqQ+LIhDJAS1ycH0xdPtd02dbx571Ag95ikC8nt+EmyQj4W32R2SkCdpf4V89hTQMtFsEsRcb9d+/L/SvPPu9fdtfOKdf46N2lI9xOaeigKUeEoJuAdwODKvSr/DgI3dEDakHx4uejxMhQpv3uTdR2JzoWqm6dlpKvZ/ZrmRdO5ZlloPn7chq95nJpGkSTo1ZrnB9W3leP7b0IJRZ+NuInZRinXKlDxU6A9pCS44RGeZIlRC9fOhqiBERAgMBAAECggEAEl0ws18kKT11lamNlNCy+2BkxdZBEaIRrHTT11vHjwv6rjyoNdPCAGiw3EakMOZgVykaV/qy6J+MlaLif8YXTD5BZnrOEHtZ2UaWaHvgVgjAqarLzt1BxTd7BvucMka13rLMo56BNbdwHEO1tfBvlLqDpF9P5c3VjyJBifeJmazmn1ayEc13d78ljcn3LkaJqQxUF3F6WD/yMHHOuhd6pe3SIzy4w36oXESMaUBOazUBjl4dMT0tkaY8rnpFbHvYqDwiLZPKSwU7AqHpgJ3W2fOU1m17fNXAwtpelZZAd1w5qVmOMHzAV/L68FyN59hPkaJ6xDlW7Jn9DZ03uk//wQKBgQDfx1bs9gT68J0BY8pQusXykGOVHCQ4laLKSL3j/5tkfCjrBGSrryieZQnwq3JeojGeCK3iP3FBjtHy+xNUBr6sUgRXkGHC5ynKOfQ4cr7MnmfBtLD7B5Wc/t77JtCeIMVIg56e7m8je35ZKrCp2OtXkJISsmxAIm89o0Fn+gAHOQKBgQDUX4jPihY5qpDbN4wTgQVOWmt9MM7KDRCTXe5GAm4XJYs+1k0wRGbYDf5MkvhjpPU9xVurBekzWvaYeL6uBRS+N9P4RyVpPUnbNdD2qCB/dwleCUvVwOP3wnCXBEOeY5QeUxYPMIOfgqbIym5IdMqp3k3wx299PexD0Qz4suHAmQKBgQC3K2fPlqEE9qbNWG1mHzugDOg2SU6ssm8JvPkSA0uiuK6VkIcGsN1Cqdm1EeEP7RtIDDqokt2Vl93qUfHEObEhRBEhy6D59FWUnlN1URc0TgUH3WtwTwj79PN4CJGCnbheXr4bwXdfR9poCjWJEXGxvziR7VIGPSlo/pSvE0eqoQKBgEmJbxEWKo37Pr95o3pZcaHo81FdJi2WiofMvaXmT4KaUHYItJ+i/82OQuD2/yVZyEWEYjJ9EHvk4oB3RudxAPbA1BgjYpPnUfyizOGr49zfNhImu+ifKxDudhgEtPlSV8aMVJYb4WfJUME0Py29qzNYs7pNBJDkcTwmVgyDvGH5AoGBAKIUq8zP7HdrFnVIR+MejRbjUuTeRMVVc2F9WPXeJ/I/yJH7WFqxyjegAnqbNIx5hEMMVN9oerKR6y+kZqs7atOcJjYmYZTdM2QqZCBAp+0ahzfXsqC7PXiVV33gMzcg7LBKqm0udr9uuF7uTqfTJyopV82k7XOz/NFSB10DFF+P
  publicKey: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuaSWjM69eluk/Dbv8WaKw3C+a3KXE4Mw3gVNmd6M7Oi7GieBa8LdsQ0zGJTPDm972vA5iX+e1UCweoypRWdNm14BsAFxa6kPiyIQyQEtcnB9MXT7XdNnW8ee9QIPeYpAvJ7fhJskI+Ft9kdkpAnaX+FfPYU0DLRbBLEXG/Xfvy/0rzz7vX3bXzinX+OjdpSPcTmnooClHhKCbgHcDgyr0q/w4CN3RA2pB8eLno8TIUKb97k3Udic6FqpunZaSr2f2a5kXTuWZZaD5+3IaveZyaRpEk6NWa5wfVt5Xj+29CCUWfjbiJ2UYp1ypQ8VOgPaQkuOERnmSJUQvXzoaogREQIDAQAB
  accountPublicKey: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt4wSbfX7WG9k8wSzfPiN0s6ibU12F3dL0BDcewhvReWmEmMtRkedPZNjXedfZ6wujDWeNtKa9zGOhI0m4rtWsGeInmXr/N2uBFN9TtImWt6XZ8OwuZq9gXxAvCl5IOJjVg5lo1aksOB4eFOB8dYcW0iEE/CjZSAImRAx48cVxCIjOlV8og6H15Om3N8qJ/aoJGf75Xy4SPOYUJPJvJipCGMK9erP7K70iED5EEiSom3KR4k7Sj8IA3dbq4cmUr34SwtvuqcQzVwGIaUBHNbjFGkze4B6Qln0Szxu/f5Mib6d8dF/ykBMeLNZENEHfUVHv6mcXjxtyyqQRdbuGTrXDwIDAQAB
  #  notifyUrl: http://26k301z145.qicp.vip:12517/api/pay/alipay/notify
  #  returnUrl: http://26k301z145.qicp.vip:12517/api/pay/alipay/return
  notifyUrl: http://127.0.0.1/api/pay/alipay/notify
  returnUrl: http://127.0.0.1/api/pay/alipay/return
  url: https://openapi.alipaydev.com/gateway.do
  charset: UTF-8
  format: json
  signType: RSA2
  #  paymentSuccessUrl: http://localhost:8080/index.html?orderNo=%s
  paymentSuccessUrl: http://127.0.0.1/confirmation
  paymentFailureUrl: http://localhost:8080/fail.html?orderNo=%s
  product_code: FAST_INSTANT_TRADE_PAY
  timeout_express: 10m


#oss
oss:
  #连接OSS地址
  endPoint: oss-cn-beijing.aliyuncs.com/
  #连接Key
  accessKeyId: LTAI4FjxqvC5wkZsUfasY5336DW
  #连接Secret
  accessKeySecret: N4BZB7zBZLuSURpnfBuGqTmad5VbS2Vp
  #存储空间名称
  bucketName: ljx-est
  #OSS文件访问域名
  ossWebUrl: ljx-est.oss-cn-beijing.aliyuncs.com/


#微信
wechat:
  access_token_url: https://api.weixin.qq.com/sns/oauth2/access_token
  appid: wx9168f76f000a0d4c
  secret: 8ba69d5639242c3bd3a69dffe84336c1
  code: code
  grant_type: authorization_code
  success_url: http://localhost:8080/index.html
  open_url: https://open.weixin.qq.com/connect/qrconnect
  redirect_uri: http://localhost:8080/api/user/wechat/callback
  response_type: code
  scope: snsapi_login
  state: STATE#wechat_redirect


#阿里云短信
ali:
  sms:
    #连接Key
    accessKeyId: LTAI4FjSDAwkZsUY5336DW
    #连接Secret
    accessKeySecret: SADJIOFS2Vp
    #模板
    templateCode: SMS_173760365
    #签名
    signName: 二手兔
    #短信产品域名
    domain: dysmsapi.aliyuncs.com
    #版本-固定值
    version: 2017-05-25
    #地区-默认即可
    regionId: default
```

3、将est-service项目的接口实现类以及工具类代码拷贝到est-provider项目中，如下所示：

![1](image\4-1.jpg)

4、给所有的接口实现类添加@Service注解，如下所示：

**注意：@Service是Dubbo包下的，"import org.apache.dubbo.config.annotation.Service;"**

![2](image\4-2.jpg)

5、在est-provider启动类添加注解，如下所示：

![](image\4-3.jpg)