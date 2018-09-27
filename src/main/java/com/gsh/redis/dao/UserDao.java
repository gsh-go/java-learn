package com.gsh.redis.dao;

import com.gsh.redis.obj.User;

/**
 * @Author: gsh
 * @Date: Created in 2018/2/1 14:29
 * @Description:
 */
public interface UserDao {
     void saveUser(User user);

     User getUser(long id);

}
