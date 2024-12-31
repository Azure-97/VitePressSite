package com.example.设计模式.创建型模式.单例模式.多线程饿汉式单例模式;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 22:54
 * @Version 1.0
 **/
public final class Singleton {
    private static Singleton instance;

    // 将实例在静态代码块中初始化
    static {
        instance = new Singleton();
    }

    private Singleton() {
        // 构造函数私有化，防止外部直接创建实例
        // 进行初始化操作
        initialize();
    }

    private void initialize() {
        // 初始化操作
        // 例如加载配置文件、建立数据库连接等
    }

    public static Singleton getInstance() {
        // 直接返回实例，无需同步
        return instance;
    }
}
