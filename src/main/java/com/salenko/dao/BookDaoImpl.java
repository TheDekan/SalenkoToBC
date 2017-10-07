package com.salenko.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.salenko.model.Book;

@Repository
@Transactional
public class BookDaoImpl extends AbstractDao implements BookDao {

    @Override
    public void update(Book row) {
        super.update(row);
    }

    @Override
    public Book insert(Book row) {
        return (Book) super.insert(row);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findAll() {
        Criteria criteria = getSession().createCriteria(Book.class);
        return (List<Book>) criteria.list();
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("delete from Book where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    //redundant method, left as example
    @Override
    public Book findById(Long id) {
        return (Book) findByField(Book.class, "id", id);
    }

    //redundant method, left as example
    @Override
    public Long getCount() {
        Query query = getSession().createQuery("select count(*) from Book");
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Book> findAllByName(String name) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.add(Restrictions.like("name", name));
        return (List<Book>) criteria.list();
    }

}
