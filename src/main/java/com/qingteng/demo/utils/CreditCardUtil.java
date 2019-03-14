package com.qingteng.demo.utils;

import java.time.LocalDate;

public interface CreditCardUtil {

    /**
     * 获取免息天数
     * @param date 日期
     * @param statementDate 结算日
     * @param dueDate 还款日
     * @return
     */
    static long getInterestholiday(LocalDate date, int statementDate, int dueDate) {

        long result = 0;

        LocalDate dueLocalDate = getDueDate(date, statementDate, dueDate);
        result = DateUtil.until(date, dueLocalDate);

        System.out.println(String.format("%s:%s:%d", date.toString(), dueLocalDate.toString(), result));
        return result;
    }

    /**
     * 获取还款日时间
     * @param date
     * @param statementDate
     * @param dueDate
     * @return
     */
     static LocalDate getDueDate(LocalDate date, int statementDate, int dueDate) {
        LocalDate dueLocalDate;

        dueLocalDate = date.plusMonths(date.getDayOfMonth() <= statementDate ? 1 : 2).withDayOfMonth(dueDate);
        return dueLocalDate;
    }
}
