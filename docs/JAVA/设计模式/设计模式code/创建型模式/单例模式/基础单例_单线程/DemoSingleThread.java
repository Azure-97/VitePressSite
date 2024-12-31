package com.example.设计模式.创建型模式.单例模式.基础单例_单线程;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 22:51
 * @Version 1.0
 **/

public class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}
