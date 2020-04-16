package com.example.demo.service.impl;

import com.example.demo.dao.IBookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private IBookDao bookDao;

    @Override
    public int add(String reader, String isbn, String title, String author, String description) {
        return bookDao.add(reader, isbn, title, author, description);
    }

    @Override
    public int update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public int delete(long id) {
        return bookDao.delete(id);
    }

    @Override
    public Book findOne(long id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> list() {
        return bookDao.list();
    }
}
