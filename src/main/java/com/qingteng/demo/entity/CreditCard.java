package com.qingteng.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class CreditCard implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 卡号
     */
    private String cardNumber;
    /**
     * 账单日
     */
    private int statementDate;
    /**
     * 还款日
     */
    private int dueDate;

    /**
     *
     */
    private String state;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     *
     */
    private String inputSource;
    /**
     * 过期时间-月
     */
    private Integer expirationMonth;
    /**
     * 过期时间-年
     */
    private Integer expirationYear;
    /**
     * 后四位
     */
    private String lastFour;




}
