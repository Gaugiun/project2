package com.stylefeng.guns.rest.common;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/8 19:12
 */
public class CurrentUser {
    // 线程绑定的存储空间
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }
    public static String getCurrentUser(){
        return threadLocal.get();
    }

}
