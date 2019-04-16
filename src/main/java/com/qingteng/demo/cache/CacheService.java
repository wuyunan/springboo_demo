package com.qingteng.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * CacheService
 *
 * @author terry
 * @date 2019-01-25
 */
@Service
public class CacheService {

    private StringRedisTemplate stringRedisTemplate;

    @Value("${validateCode.bypass:false}")
    private boolean validateCodeBypass;

    @Autowired
    private Environment env;

    public CacheService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String add(CacheType type, String key, String value) throws Exception {
        key = type + ":" + key;
        // 判断是验证码还是邀请链接
        if (type == CacheType.VALIDATECODE) {
            if (stringRedisTemplate.hasKey(key)) {
                return stringRedisTemplate.opsForValue().get(key);
            } else {
                stringRedisTemplate.opsForValue().set(key, value);
                stringRedisTemplate.expireAt(key, new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("valid.duration"))));
            }
        } else if (type == CacheType.REFERENCELINK) {
            stringRedisTemplate.opsForValue().set(key, value);
            stringRedisTemplate.expireAt(key, new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("reference.duration"))));
        }
        return value;
    }

    public Boolean verify(CacheType type, String key, String value) {
        if (null == key || key.isEmpty() || null == value || value.isEmpty()) {
            return false;
        }

        if (validateCodeBypass) {
            return true;
        }

        key = type + ":" + key;
        return stringRedisTemplate.hasKey(key) && stringRedisTemplate.opsForValue().get(key).equals(value) && stringRedisTemplate.delete(key);
    }

    public String getReferrerId(String key) {
        key = CacheType.REFERENCELINK + ":" + key;
        return stringRedisTemplate.opsForValue().get(key);
    }

    public String getCodeByKey(String key) {
        return stringRedisTemplate.opsForValue().get(CacheType.VALIDATECODE + ":" + key);
    }

    public void add(String key, String value, String expires_in) throws Exception {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expireAt(key, new Date(System.currentTimeMillis() + Long.parseLong(expires_in)));
    }

    public void add(String key, String value, Date expires_in) throws Exception {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expireAt(key, expires_in);
    }

    public String get(String key) throws Exception {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
