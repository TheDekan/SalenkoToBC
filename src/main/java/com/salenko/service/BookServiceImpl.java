package com.salenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salenko.dao.BookDao;
import com.salenko.model.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao dao;

    @Override
    public void update(Book row) {
        dao.update(row);
    }

    @Override
    public Book insert(Book row) {
        return dao.insert(row);
    }

    @Override
    public List<Book> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    //redundant method, left as example
    @Override
    public Book findById(Long id) {
        return dao.findById(id);
    }

    //redundant method, left as example
    @Override
    public Long getCount() {
        return dao.getCount();
    }

    @Override
    public List<Book> findAllByName(String name) {
        return dao.findAllByName(name);
    }

}
