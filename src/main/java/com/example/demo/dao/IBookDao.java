package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IBookDao {

    @Insert("insert into book(reader, isbn, title, author, description) values (#{reader}, #{isbn}, #{title}, #{author}, #{description})")
    int add(@Param("reader") String reader, @Param("isbn") String isbn, @Param("title") String title, @Param("author") String author, @Param("description") String description);

    @Update("update book set description = #{book.description} where id = #{book.id}")
    int update(@Param("book") Book book);

    @Delete("delete from book where id = #{id}")
    int delete(@Param("id") long id);

    @Select("select * from book where id = #{id}")
    Book findOne(@Param("id") long id);

    //todo mapper和@不能同时存在
//    @Select("select * from book where id is not null")
    List<Book> list();

}
