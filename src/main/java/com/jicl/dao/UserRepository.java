package com.jicl.dao;

import com.jicl.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 10:28
 * @Description: 用户管理持久层接口
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username 1
     * @param password 2
     * @return: com.jicl.pojo.User
     * @auther: xianzilei
     * @date: 2019/11/24 10:32
     **/
    User findByUsernameAndPassword(String username, String password);
}
