package com.qingteng.demo.sms;

import com.qingteng.demo.cache.CacheType;
import com.qingteng.demo.error.TooManyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * SMSService
 *
 * @author terry
 * @date 2019-01-28
 */
@Service
public class SMSService {
  @Autowired
  private AliSMSService aliSmsService;

  @Autowired
  private YunpianSMSServiceService yunpianSmsService;

  @Value("${sms.enabled:false}")
  private boolean enabled;

  @Value("${sms.validate.max:10}")
  private Long validateMaxTimes;

  private StringRedisTemplate stringRedisTemplate;

  public SMSService(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  public void sendSMS(String mobile, String code) throws Exception {
    checkBeforeSend(mobile);

    if (!enabled) {
      return;
    }

    try {
      aliSmsService.sendSMS(mobile, code);
    } catch (Exception e) {
      e.printStackTrace();
      yunpianSmsService.sendSMS(mobile, code);
    }
  }

  public void checkBeforeSend(String key) throws Exception {
    String timesKey = "TIMES:" + CacheType.VALIDATECODE + ":" + key;
    if (stringRedisTemplate.hasKey(timesKey)
            && Integer.parseInt(stringRedisTemplate.opsForValue().get(timesKey)) >= validateMaxTimes) {
      throw new TooManyException("Too many request!");
    } else {
      Integer times;
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR, 23);
      calendar.set(Calendar.MINUTE, 59);
      calendar.set(Calendar.SECOND, 59);
      calendar.set(Calendar.MILLISECOND, 999);
      if (!stringRedisTemplate.hasKey(timesKey)) {
        times = 1;
      } else {
        times = Integer.parseInt(stringRedisTemplate.opsForValue().get(timesKey));
      }
      stringRedisTemplate.opsForValue().getAndSet(timesKey, String.valueOf(times + 1));
      stringRedisTemplate.expireAt(timesKey, calendar.getTime());
    }
  }
}
