package com.example.demo.dao;

import com.example.demo.entity.UserAgain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * JpaRepository本身就包含了常用功能，剩下的查询我们按照规范写接口即可，
 * JPA支持@Query注解写HQL，也支持findAllByUsername这种根据字段名命名的方式
 */
public interface IUserAgainRepository extends JpaRepository<UserAgain, Integer> {

    /**
     * 使用原生sql需要 nativeQuery = true
     *
     * @param username
     * @return
     */
    @Query(value = "select * from user_again where username = ?1 ", nativeQuery = true)
    UserAgain findByUsername(String username);


    @Query(value = "from UserAgain where age = ?1 order by id")
    List<UserAgain> queryByAge(int age);


    /**
     * delete update 语句中需要添加@Modifying注解，还需要根据需要添加事务注解
     *
     * @param id
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "delete from user_again where id = ?1", nativeQuery = true)
    int deleteAgain(int id);

    UserAgain findTopByAgeEqualsOrderByIdDesc(int age);
}
