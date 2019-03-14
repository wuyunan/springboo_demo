package com.qingteng.demo.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;


public class CreditCard implements Serializable {

    @Id
    private String id;
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

    public CreditCard() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(int statementDate) {
        this.statementDate = statementDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getLastFour() {
        return lastFour;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }
}
