package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data magic :)
public interface BookRepository extends JpaRepository<Book, Long> {
}