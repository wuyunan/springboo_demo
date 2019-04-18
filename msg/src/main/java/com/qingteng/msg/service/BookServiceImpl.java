package com.qingteng.msg.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(interfaceClass = IBookService.class)
@Component
public class BookServiceImpl implements IBookService {
    @Override
    public String sendSms(String mobile, String content, String platform) {
        try {
            Thread.sleep(2000);// 模拟调用短信接口费时2s
            return String.format("发送结果: %s, 手机号: %s, 内容: %s, 平台: %s", "SUCCESS", mobile, content, platform);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
