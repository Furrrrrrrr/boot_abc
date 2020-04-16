package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description
 * @Author
 * @Date 2020-04-15
 */

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 578950628540566468L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "reader")
    private String reader;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

}
