package com.jicl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import com.jicl.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private BlogService blogService;

    @Test
    public void test(){
        PageInfo<Blog> page = blogService.page(null, 1, 10);
        String s = JSON.toJSONString(page);
        System.out.println(s);
        System.out.println(new Date());
    }

}
