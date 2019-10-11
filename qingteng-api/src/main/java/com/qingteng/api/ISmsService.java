package com.qingteng.api;

public interface ISmsService {

    /**
     * 定义一个发短信的接口
     * @param mobile 手机号
     * @param content 内容
     * @param platform 平台，分别对应 LIANTONG, YIDONG, DIANXIN
     * @return 正常返回发送成功，失败即可，这里为了展示发送的手机号与内容平台，直接把内容再返回去
     */
    String sendSms(String mobile, String content, String platform);

}