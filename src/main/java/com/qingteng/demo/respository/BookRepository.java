package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Book;
import com.qingteng.demo.projection.CustomBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Spring Data magic :)
@RepositoryRestResource(excerptProjection = CustomBook.class)

public interface BookRepository extends JpaRepository<Book, Long> {
}