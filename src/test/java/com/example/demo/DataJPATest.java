package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.IUserAgainRepository;
import com.example.demo.entity.UserAgain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataJPATest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void t2() throws Exception {
        UserAgain again = new UserAgain();
        again.setAge(1);
        again.setUsername("name1");
        again.setPassword("word1");
        Object content = JSONObject.toJSON(again);
        MvcResult result = mockMvc.perform(post("/user/again/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.err.println(contentAsString);
    }

    @Test
    public void t1() throws Exception {
        mockMvc.perform(get("/user/again/queryByAge/1"))
                .andDo(print());
    }

    @Test
    @Transactional
    @Rollback
    public void t3() throws Exception {
        mockMvc.perform(delete("/user/again/delele/3"))
                .andDo(print());
    }

    @Autowired
    private IUserAgainRepository userAgainRepository;

    @Test
    public void t4() {
        UserAgain topByAge = userAgainRepository.findTopByAgeEqualsOrderByIdDesc(1);
        System.err.println(topByAge);
    }


}
