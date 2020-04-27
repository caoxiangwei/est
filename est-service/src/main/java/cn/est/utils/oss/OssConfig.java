package cn.est.utils.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Oss配置类
 * @Date 2019-08-20 16:56
 * @Author Liujx
 * Version 1.0
 **/

@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String ossWebUrl;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getOssWebUrl() {
        return ossWebUrl;
    }

    public void setOssWebUrl(String ossWebUrl) {
        this.ossWebUrl = ossWebUrl;
    }
}