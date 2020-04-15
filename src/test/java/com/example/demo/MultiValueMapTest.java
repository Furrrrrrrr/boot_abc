package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiValueMapTest {

    private MultiValueMap<String, String> map;

    @Before
    public void setUp() {
        map = new LinkedMultiValueMap<>();
    }


    /**
     * MultiValueMap 调试测试
     * 调试看一下 内部数据结构,其内部是一个LinkedList ??? 待验证
     */
    @Test
    public void multiValueMapAdd() {
        map.add("key", "value1");
        map.add("key", "value2");
        assertEquals(1, map.size());
        List<String> expected = new ArrayList<String>(2);
        expected.add("value1");
        expected.add("value2");
        assertEquals(expected, map.get("key"));
    }

    @Test
    public void multiValueMapGetFirst() {
        List<String> values = new ArrayList<String>(2);
        values.add("value1");
        values.add("value2");
        map.put("key", values);
        assertEquals("value1", map.getFirst("key"));
        assertNull(map.getFirst("other"));
    }

    @Test
    public void multiValueMapSet() {
        map.set("key", "value1");
        map.set("key", "value2");
        assertEquals(1, map.size());
        assertEquals(Collections.singletonList("value2"), map.get("key"));
    }

    @Test
    public void multiValueMapEquals() {
        map.set("key1", "value1");
        assertEquals(map, map);
        MultiValueMap<String, String> o1 = new LinkedMultiValueMap<String, String>();
        o1.set("key1", "value1");
        assertEquals(map, o1);
        assertEquals(o1, map);
        Map<String, List<String>> o2 = new HashMap<String, List<String>>();
        o2.put("key1", Collections.singletonList("value1"));
        assertEquals(map, o2);
        assertEquals(o2, map);
    }

}
