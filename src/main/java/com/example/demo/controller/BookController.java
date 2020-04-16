package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public int add(@RequestBody Book book) {
        return bookService.add(book.getReader(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }

    @PutMapping("/update")
    public int update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/delete")
    public int delete(long id) {
        return bookService.delete(id);
    }

    @GetMapping("/get")
    public Book findOne(long id) {
        return bookService.findOne(id);
    }

    @GetMapping("/list")
    public List<Book> list() {
        return bookService.list();
    }
}
