package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String string);
}