package com.example.设计模式.创建型模式.单例模式.多线程懒汉式单例模式;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 23:15
 * @Version 1.0
 **/
public final class Singleton {
    private static volatile Singleton instance;
    public String value;

    private Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(value);
                }
            }
        }
        return instance;
    }
}
