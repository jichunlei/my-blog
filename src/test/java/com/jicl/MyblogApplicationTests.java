package com.jicl;

import com.jicl.constant.RedisConstant;
import com.jicl.entity.Type;
import com.jicl.es.EsBlogDo;
import com.jicl.es.EsBlogRepository;
import com.jicl.es.EsBlogService;
import com.jicl.util.RedisValueUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private ValueOperations<String, Serializable> valueOperations;

    @Autowired
    private HashOperations<String, String, Serializable> hashOperations;

    @Autowired
    private ListOperations<String, Serializable> listOperations;

    @Autowired
    private SetOperations<String, Serializable> setOperations;

    @Autowired
    private ZSetOperations<String, Serializable> zSetOperations;

    @Autowired
    private EsBlogService esBlogService;

    @Autowired
    private RedisValueUtil redisValueUtil;

    @Test
    public void testInsert() {
        EsBlogDo esBlogDo = new EsBlogDo();
        esBlogDo.setBlogId(1);
        esBlogDo.setBlogTitle("博客标题1");
        esBlogDo.setBlogDesc("博客描述1");
        esBlogDo.setBlogContent("博客内容1：贤子磊1");
        esBlogRepository.save(esBlogDo);
        EsBlogDo esBlogDo2 = new EsBlogDo();
        esBlogDo2.setBlogId(2);
        esBlogDo2.setBlogTitle("博客标题2");
        esBlogDo2.setBlogDesc("博客描述2");
        esBlogDo2.setBlogContent("博客内容2：贤子磊2");
        esBlogRepository.save(esBlogDo2);
    }

    @Test
    public void testUpdate() {
        EsBlogDo esBlogDo = new EsBlogDo();
        esBlogDo.setBlogId(1);
        esBlogDo.setBlogTitle("博客标题2");
        esBlogDo.setBlogContent("博客内容：贤子磊哈哈哈哈");
        esBlogRepository.save(esBlogDo);
    }

    @Test
    public void testDelete() {
        esBlogRepository.deleteById(1);
    }

    @Test
    public void testSelectById() {
        Optional<EsBlogDo> optionalEsBlogDo = esBlogRepository.findById(1);
        System.out.println(optionalEsBlogDo.get());
    }

    @Test
    public void testRedis() {
        Type type1 = new Type();
        type1.setTypeName("测试1");
        Type type2 = new Type();
        type2.setTypeName("测试2");
        valueOperations.set("k1",type1);
        listOperations.leftPush("k2",type1);
        listOperations.leftPush("k2",type2);
        List<Serializable> list = listOperations.range("k2", 0, -1);
        System.out.println(list);
        hashOperations.put("k3","type1",type1);
        Object o = hashOperations.get("k3", "type1");
        System.out.println(o);
        setOperations.add("k4",type1);
        setOperations.add("k4",type1);
        setOperations.add("k4",type2);
        setOperations.add("k4",type2);
        zSetOperations.add("k5",type1,1.0);
        zSetOperations.add("k5",type1,1.1);
        zSetOperations.add("k5",type2,1.2);
        zSetOperations.add("k5",type2,1.3);
    }

    @Test
    public void test(){
        Page<EsBlogDo> result = esBlogService.search("Redis", 0, 10,null);
        System.out.println(result);
    }

    @Test
    public void test1(){
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"1",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"2",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"3",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"4",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"5",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"6",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"7",new HashSet<Integer>());
        redisValueUtil.hPut(RedisConstant.LIKE_KEY,"8",new HashSet<Integer>());
    }

}
