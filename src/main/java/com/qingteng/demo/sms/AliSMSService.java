package com.qingteng.demo.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * AliSMSService
 *
 * @author terry
 * @date 2019-01-28
 */
@Service
public class AliSMSService {

  @Value("${sms.ali.access-key-id}")
  String accessKeyId;

  @Value("${sms.ali.template-code}")
  String templateCode;

  @Value("${sms.sign-name}")
  String signName;

  public AliSMSService() {
  }

  public void sendSMS(String mobile, String code) throws Exception {
    DefaultProfile profile = DefaultProfile.getProfile(
            "default",
            accessKeyId,
            System.getenv("ALI_SECRET_ACCESS_KEY"));

    IAcsClient client = new DefaultAcsClient(profile);
    CommonRequest request = new CommonRequest();
    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");

    request.putQueryParameter("PhoneNumbers", mobile);
    request.putQueryParameter("SignName", signName);
    request.putQueryParameter("TemplateCode", templateCode);
    request.putBodyParameter("TemplateParam", "{\"code\": \"" + code + "\"}");

    CommonResponse response = client.getCommonResponse(request);
    JSONObject jsonObject = (JSONObject) (JSONObject.parse(response.getData()));
    if (!jsonObject.get("Message").equals("OK")) {
      throw new Exception((String) jsonObject.get("Message"));
    }
  }
}
