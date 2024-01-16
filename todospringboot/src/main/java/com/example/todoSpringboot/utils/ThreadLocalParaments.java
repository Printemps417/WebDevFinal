package com.example.todoSpringboot.utils;

/**
 * ClassName: ThreadLocalParaments
 * Package: com.example.todoSpringboot.utils
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/28 11:07
 * @Version 1.0
 */
public class ThreadLocalParaments {

    // 为不同类型的局部变量创建不同的 ThreadLocal 实例
    private static final ThreadLocal<String> StuIdLocal = new ThreadLocal<>();

    // 设置和获取 Integer 类型的局部变量
    public static void setStuId(String value) {
        StuIdLocal.set(value);
    }

    public static String getStuId() {
        return StuIdLocal.get();
    }

    // 清除所有线程局部变量
    public static void removeAll() {
        StuIdLocal.remove();
    }
}

