package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {

    int add(String reader, String isbn, String title, String author, String description);

    int update(Book book);

    int delete(long id);

    Book findOne(long id);

    List<Book> list();
}
