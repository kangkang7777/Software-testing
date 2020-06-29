package com.xuezhi.user;

import com.xuezhi.user.adapter.output.UserRepositoryImpl;

import com.xuezhi.user.domain.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserApplicationTests {

    @Autowired
    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    /*
     * 设置超时时间
     */
    @Test
    public void testUpdateUser() {
        //输入id为空字符串
        assertEquals(false,userRepository.updateUser("", "Jeff", 12, "male", "123"));

        //输入id对应的用户不存在
        assertEquals(false,userRepository.updateUser("123", "Jeff", 12, "male", "123"));

        //输入参数错误
        assertEquals(false,userRepository.updateUser("5ef89d5433e418418491ba50", "Jeff", -1, "male", "123"));
        assertEquals(false,userRepository.updateUser("5ef89d5433e418418491ba50", "Jeff", 13, "test", "123"));

        //合法输入
        assertEquals(true,userRepository.updateUser("5ef89d5433e418418491ba50", "Jeff", 13, "male", "123"));
        System.out.println("测试进行中");
    }

    @Test
    public void testGetUserById(){
        User user = new User();
        user.setId("5ef89d5433e418418491ba50");
        user.setName("Jeff");
        user.setAge(13);
        user.setSex("male");
        user.setSignature("123");
        user.setEmail("865150663@qq.com");
        user.setPassword("123456");

        //输入id为空字符串
        assertEquals(null,userRepository.getUserById(""));

        //输入id对应的用户不存在
        assertEquals(null,userRepository.getUserById("123"));

        User temp = userRepository.getUserById("5ef89d5433e418418491ba50");
        //合法输入
        assertEquals(user,userRepository.getUserById("5ef89d5433e418418491ba50"));


    }

    @Test
    public void testAddFollowList(){
        //输入参数为空字符串
        assertEquals(false, userRepository.addFollowListId("", "5ef99259e7c16d4b111c8f11"));
        assertEquals(false, userRepository.addFollowListId("5ef89d5433e418418491ba50", ""));

        //输入id不存在
        assertEquals(false, userRepository.addFollowListId("12345", "5ef99259e7c16d4b111c8f11"));
        assertEquals(false, userRepository.addFollowListId("5ef9b8ef72ab6ce120a39a76", "1231234"));


        //用户已有相同的follow的question的id
        assertEquals(false, userRepository.addFollowListId("5ef89d5433e418418491ba50", "5ef9b44e72ab6ce120a39a75"));

        //合法输入
        assertEquals(true, userRepository.addFollowListId("5ef89d5433e418418491ba50", "5ef9b42872ab6ce120a39a74"));
    }

    @Test
    public void testDeleteQuestion(){
        assertEquals(false, userRepository.deleteQuestionId("","001"));
        assertEquals(false, userRepository.deleteQuestionId("01",""));
        assertEquals(false, userRepository.deleteQuestionId("()@@aa","001"));
        assertEquals(false, userRepository.deleteQuestionId("01","()@@aa"));
        assertEquals(false, userRepository.deleteQuestionId("5ef89d5433e418418491ba50","5ef9b42872ab6ce120a39a74"));
        assertEquals(true, userRepository.deleteQuestionId("5ef89d5433e418418491ba50","5ef9b44e72ab6ce120a39a75"));
    }

}
