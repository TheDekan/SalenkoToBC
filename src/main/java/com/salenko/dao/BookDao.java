package com.salenko.dao;

import java.util.List;

import com.salenko.model.Book;

public interface BookDao {

    void update(Book row);

    Book insert(Book row);

    List<Book> findAll();

    void delete(Long id);

    //redundant method, left as example
    Book findById(Long id);

    List<Book> findAllByName(String name);

    //redundant method, left as example
    Long getCount();

}
