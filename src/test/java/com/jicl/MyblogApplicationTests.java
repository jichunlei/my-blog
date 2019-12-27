package com.jicl;

import com.jicl.es.EsBlogDo;
import com.jicl.es.EsBlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private EsBlogRepository esBlogRepository;

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

}
