package com.gsh.redis;

import com.gsh.redis.utils.RedisClusterUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gsh
 * @Date: Created in 2018/2/2 11:24
 * @Description:
 */
public class RedisClusterApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-redis-cluster.xml");
        RedisClusterUtils redisClusterUtils = (RedisClusterUtils) ctx.getBean("redisClusterUtils");
      /*  List list = new ArrayList<String>();
        String str = "";*/
        for(int i = 0;i<100000000;i++){
            /*str = String.valueOf(i).toString();
            list.add(str);*/
            redisClusterUtils.set("str-3-"+i, "StringValue@"+i);
        }
        //redisClusterUtils.set("list", list);
        System.out.println(redisClusterUtils.get("list"));

    }


}
