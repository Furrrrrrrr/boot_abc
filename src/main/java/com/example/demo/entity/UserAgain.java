package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * jpa entity
 * todo @Entity 表明是一个映射的实体类， @Id表明id， @GeneratedValue 字段自动生成
 *
 * @GeneratedValue(strategy = GenerationType.IDENTITY)自增策略，不需要映射的字段可以通过@Transient注解排除掉
 */
@Data
@Entity
@Table(name = "user_again")
@AllArgsConstructor
@NoArgsConstructor
// todo final修饰或者@NotNull注释
//@RequiredArgsConstructor
public class UserAgain {

    /**
     * 常见的几种自增策略
     * TABLE： 使用一个特定的数据库表格来保存主键
     * SEQUENCE： 根据底层数据库的序列来生成主键，条件是数据库支持序列。这个值要与generator一起使用，generator 指定生成主键使用的生成器（可能是orcale中自己编写的序列）。
     * IDENTITY： 主键由数据库自动生成（主要是支持自动增长的数据库，如mysql）
     * AUTO： 主键由程序控制，也是GenerationType的默认值。
     */

    @Id
    @GeneratedValue
    private int id;
    private int age;
    private String password;
    private String username;


}
