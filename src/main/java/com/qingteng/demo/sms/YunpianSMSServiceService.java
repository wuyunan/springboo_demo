package com.qingteng.demo.sms;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * YunpianSMSServiceService
 *
 * @author terry
 * @date 2019-01-28
 */
@Service
public class YunpianSMSServiceService {

	@Value("${sms.sign-name}")
	String signName;

	public YunpianSMSServiceService() {
	}

	public void sendSMS(String mobile, String code) throws Exception {
		YunpianClient client = new YunpianClient(System.getenv("YUNPIAN_API_KEY")).init();
		Map<String, String> param = client.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, "【" + signName + "】您的验证码是" + code);
		Result<SmsSingleSend> result = client.sms().single_send(param);
		if (result.getCode() != 0) {
			throw new Exception(result.getDetail());
		}
		client.close();
	}
}
