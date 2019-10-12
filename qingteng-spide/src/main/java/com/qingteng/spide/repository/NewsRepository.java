package com.qingteng.spide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.qingteng.spide.entity.News;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}