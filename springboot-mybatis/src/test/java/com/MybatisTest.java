package com;


import com.mapper.UserMapper;
import com.pojo.user;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        List<user> users = userMapper.queryUserList();
        System.out.println(users);
    }
    @Test
    public void test1(){
        List<user> users = userMapper.queryUserList();
        System.out.println(users);
    }

}
