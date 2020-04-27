package cn.est.utils.sms;

import cn.est.constants.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/

/**
 * 阿里云短信发送
 */
@Component
public class SendSms {

    Logger log = LoggerFactory.getLogger(SendSms.class);

    @Autowired
    private AliSmsConfig aliSmsConfig;


    /**
     * 发送单挑短信
     * @param phone
     * @param code
     */
    public String send(String phone, String code) {
        String result = null;
        DefaultProfile profile = DefaultProfile.getProfile("default", aliSmsConfig.getAccessKeyId()
                , aliSmsConfig.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        CommonResponse response = null;
        request.setMethod(MethodType.POST);
        request.setDomain(aliSmsConfig.getDomain());
        request.setVersion(aliSmsConfig.getVersion());
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", aliSmsConfig.getRegionId());
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", aliSmsConfig.getSignName());
        request.putQueryParameter("TemplateCode", aliSmsConfig.getTemplateCode());
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
        try {
            response = client.getCommonResponse(request);
            log.info("阿里云发送短信返回值：{}", response.getData());
            // {"Message":"OK","RequestId":"6B5E6B36-DFC0-4859-A9FC-5DEAF04162C8","BizId":"362515768112966744^0","Code":"OK"}
            JSONObject jsonObject = JSON.parseObject(response.getData());
            if(Constants.Sms.ALI_SMS_RESSULT.equals(jsonObject.get("Code"))){
                result = Constants.Sms.ALI_SMS_RESSULT;
            }else {
                result = jsonObject.get("Message").toString();
            }
        } catch (ServerException e) {
            log.error("阿里云发送短信失败，服务端错误：{}", e);
        } catch (ClientException e) {
            log.error("阿里云发送短信失败，客户端错误：{}", e);
        }
        return result;
    }


    /*public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FjxqvC5wkZsUY5336DW", "N4BZB7zBZLuSURpnfBuGqTm5VbS2Vp");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "13521214507");
        request.putQueryParameter("SignName", "二手兔");
        request.putQueryParameter("TemplateCode", "SMS_173760365");
        request.putQueryParameter("TemplateParam", "{code:1234}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }*/
}