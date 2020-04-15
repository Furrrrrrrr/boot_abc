package com.example.demo;


import com.example.demo.dao.IUserDao;
import com.example.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * todo 其中，classes属性指定启动类，SpringBootTest.WebEnvironment.RANDOM_PORT经常和测试类中@LocalServerPort一起在注入属性时使用。会随机生成一个端口号。
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest()
public class DemoApplicationTests {

    //    @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.err.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    /**
     * 向"/test"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        // todo 路径待修改
        ResponseEntity<String> response = this.testRestTemplate.getForEntity(
                this.base.toString() + "/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));

    }


    @Test
    public void contextLoads() {
    }

    @Autowired
    IUserDao userDao;

    @Test
    public void testAdd() {
        User user = new User();
        user.setAge(12);
        user.setUsername("12");
        user.setPassword("23");
        // todo 1.yml中mysql时区设置为utc，DB中create_time字段属性为date，入库后值为2020-04-15
        // todo 2.yml中mysql时区设置为utc，DB中create_time字段属性为dateTime，入库后值为2020-04-15 01:40:33 当前插入时间为2020-04-15 09:40:33 utc存在时差问题
        // todo 3.yml中mysql配置文件中serverTimezone=GMT%2B8 时差问题解决
        user.setCreateTime(new Date());
        int add = userDao.add(user);
        // todo add后没有自动返显id
        System.err.println(user);
    }

    @Test
    public void testQueryOne() {
        int id = 3;
        ResponseEntity<User> responseEntity = this.testRestTemplate.getForEntity(this.base.toString() + "/user/" + id, User.class, "");
        System.err.println(responseEntity.getBody());
    }

    @Test
    public void testGetById() {
        int id = 4;
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        User result = this.testRestTemplate.getForObject(this.base.toString() + "/user/get?id={id}", User.class, map);
        System.err.println(result);
    }


}
