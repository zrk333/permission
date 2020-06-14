package com.zrk.config.webmvc;

import com.zrk.model.User;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
public class RequestHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();

    public static void add(User user){
        userHolder.set(user);
    }

    public static User getCurrentUser(){
        return userHolder.get();
    }

    public static void remove(){
        userHolder.remove();
    }
}
