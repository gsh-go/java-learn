package com.gsh.redis;

import com.gsh.redis.dao.UserDao;
import com.gsh.redis.obj.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: gsh
 * @Date: Created in 2018/2/1 14:29
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext-redis.xml");
        UserDao userDAO = (UserDao)ac.getBean("userDAO");
        User user1 = new User();
        user1.setId(2);
        user1.setName("oba222ma");
        userDAO.saveUser(user1);
        User user2 = userDAO.getUser(1);
        System.out.println(user2.getName());
    }
}
