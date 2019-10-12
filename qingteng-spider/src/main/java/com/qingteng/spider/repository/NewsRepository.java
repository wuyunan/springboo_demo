package com.qingteng.spider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.qingteng.spider.entity.News;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}