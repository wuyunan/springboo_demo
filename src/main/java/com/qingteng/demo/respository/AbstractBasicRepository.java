package com.qingteng.demo.respository;

import java.util.List;
import java.util.Map;

/**
 * @DESC:AbstratCommonRepository
 */
public interface AbstractBasicRepository<T> {

    /**
     * 定义参数查询
     * @param params
     * @return
     */
    T findOneByParams(Map<String, Object> params);

    /**
     * 定义参数查询
     * @param params
     * @return
     */
    List<T> findByParams(Map<String, Object> params);

    /**
     * 定义参数分页查询
     * @param params
     * @return
     */
    List<T> findWithPageByParams(Map<String, Object> params);
}