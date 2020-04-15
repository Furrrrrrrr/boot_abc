package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.IUserDao;
import com.example.demo.entity.User;
import com.example.demo.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * todo 其中，classes属性指定启动类，SpringBootTest.WebEnvironment.RANDOM_PORT经常和测试类中@LocalServerPort一起在注入属性时使用。会随机生成一个端口号。
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    //@LocalServerPort 提供了 @Value("${local.server.port}") 的代替
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate testRestTemplate;


    /**
     * AsyncRestTemplate @deprecated as of Spring 5.0, in favor of {@link org.springframework.web.reactive.function.client.WebClient}
     * 推荐使用Spring 5中的WebClient。WebClient是Spring 5的响应式Web框架Spring WebFlux的一部分，位于spring-webflux项目中。
     *
     * <dependency>
     * <groupId>org.springframework.boot</groupId>
     * <artifactId>spring-boot-starter-webflux</artifactId>
     * </dependency>
     */
    private AsyncRestTemplate asyncRestTemplate;

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

    /**
     * testRestTemplate get方法 restful
     */
    @Test
    public void testQueryOne() {
        int id = 3;
        ResponseEntity<User> responseEntity = this.testRestTemplate.getForEntity(this.base.toString() + "/user/" + id, User.class, "");
        System.err.println(responseEntity.getBody());
    }

    /**
     * testRestTemplate get方法
     */
    @Test
    public void testGetById() {
        int id = 4;
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        User result = this.testRestTemplate.getForObject(this.base.toString() + "/user/get?id={id}", User.class, map);
        System.err.println(result);
    }

    /**
     * testRestTemplate post方法
     */
    @Test
    public void testAdd1() {

        Map request = new HashMap();
        request.put("username", "name1");
        request.put("password", "word1");
        request.put("age", 123);
        request.put("createTime", DateUtils.formatDate());
        int result = this.testRestTemplate.postForObject("/user/add", request, Integer.class);
        System.err.println(result);
    }

    /**
     * testRestTemplate post方法
     * 文件上传测试
     * 尚无接口，待测试
     */
    @Test
    public void upload() throws Exception {
        Resource resource = new FileSystemResource("/home/lake/github/wopi/build.gradle");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "lake");
        multiValueMap.add("files", resource);
        User result = testRestTemplate.postForObject("/test/upload", multiValueMap, User.class);
        assertEquals(result.getId(), 0);

    }

    /**
     * testRestTemplate exchange方法
     * 文件下载测试
     * 尚无接口，待测试
     */
    @Test
    public void download() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<byte[]> response = testRestTemplate.exchange("/test/download?username={1}", HttpMethod.GET, formEntity, byte[].class, urlVariables);
        if (response.getStatusCode() == HttpStatus.OK) {
//            Files.write(response.getBody(), new File("/home/lake/github/file/test.gradle"));
            Files.write(Paths.get("c:/output.txt"), response.getBody());
        }
    }

    /**
     * testRestTemplate exchange方法
     * 请求头信息测试
     * 调用exchange方法
     */
    @Test
    public void getHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        // todo exchange中的参数待研究
        ResponseEntity<User> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET, formEntity, User.class, urlVariables);
        assertEquals(result.getBody().getId(), 0);
    }

    /**
     * testRestTemplate exchange方法
     * put请求测试
     * 调用exchange方法
     */
    @Test
    public void putHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxxx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        ResponseEntity<User> result = testRestTemplate.exchange("/test/putHeader", HttpMethod.PUT, formEntity, User.class);
        assertEquals(result.getBody().getId(), 0);
    }

    @Test
    public void putTest() {
        Map request = new HashMap();
        request.put("username", "update1");
        request.put("password", "word1update");
        request.put("age", 34);
        request.put("id", 7);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<Integer> exchange = testRestTemplate.exchange("/user/update", HttpMethod.PUT, entity, Integer.class);
        assertEquals(1, (int) exchange.getBody());

    }

    /**
     * testRestTemplate exchange方法
     * delete请求测试
     * 调用exchange方法
     */
    @Test
    public void delete() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap, headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<User> result = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE, formEntity, User.class, urlVariables);
        assertEquals(result.getBody().getId(), 0);
    }

    @Transactional
    @Rollback
    @Test
    public void deleteTest() {
        int id = 5;
        ResponseEntity<Integer> exchange = testRestTemplate.exchange("/user/" + id, HttpMethod.DELETE, null, Integer.class);
        assertEquals(1, (int) exchange.getBody());
    }


    /**
     * - 异步调用要使用AsyncRestTemplate。 它是RestTemplate的扩展，提供了异步http请求处理的一种机制，通过返回ListenableFuture对象生成回调机制，以达到异步非阻塞发送http请求
     * 已废弃，替换方案
     * com.alibaba.fastjson.JSONObject fastJson
     */

    public String asyncReq() {
        String url = "http://localhost:8080/jsonAsync";
        ListenableFuture<ResponseEntity<JSONObject>> future = asyncRestTemplate.getForEntity(url, JSONObject.class);
        future.addCallback(new SuccessCallback<ResponseEntity<JSONObject>>() {
            public void onSuccess(ResponseEntity<JSONObject> result) {
                System.out.println(result.getBody().toJSONString());
            }
        }, new FailureCallback() {
            public void onFailure(Throwable ex) {
                System.out.println("onFailure:" + ex);
            }
        });
        return "this is async sample";
    }

}
