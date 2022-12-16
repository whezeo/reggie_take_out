package com.cuit.reggie.vo.utils;

/**
 *
 * ThreadLocal工具类
 */
public class UserThreadLocal {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long id){
        threadLocal.set(id);
    }
    public static Long get(){
        return  threadLocal.get();
    }

}
