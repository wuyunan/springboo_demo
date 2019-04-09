package com.qingteng.demo.projection;

import com.qingteng.demo.entity.Author;
import com.qingteng.demo.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(
        name = "customBook",
        types = {Book.class})
public interface CustomBook {
  @Value("#{target.id}")
  long getId();

  String getTitle();

  List<Author> getAuthors();

  @Value("#{target.getAuthors().size()}")
  int getAuthorCount();
}