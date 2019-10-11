package com.qingteng.demo.cache;

import de.pentabyte.springfox.ApiEnum;

/**
 * Cache
 *
 * @author terry
 * @date 2019-01-27
 */
public enum CacheType {
    /**
     * 验证码
     */
    @ApiEnum("验证码")
    VALIDATECODE("VALIDATECODE"),

    /**
     * 邀请链接
     */
    @ApiEnum("邀请链接")
    REFERENCELINK("VARIFYCODE"),

    /**
     * 每日提现
     */
    @ApiEnum("每日提现")
    WITHDRAWAL("WITHDRAWAL");

    private String value;

    private CacheType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
