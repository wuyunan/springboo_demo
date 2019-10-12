package com.qingteng.spider.repository;

import com.qingteng.spider.entity.YieldCurve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface YieldCurveRepository extends JpaRepository<YieldCurve, Long> {

}