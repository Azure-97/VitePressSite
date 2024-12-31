package com.example.设计模式.创建型模式.单例模式.基础单例_单线程;

/**
 * @description: 实现一个粗糙的单例非常简单。 你仅需隐藏构造函数并实现一个静态的构建方法即可。
 * @author: Azure
 * @date: 2024/8/27 周二 22:48
 * @Version 1.0
 **/
public final class Singleton {
    private static Singleton instance;
    public String value;

    private Singleton(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
