package com.example.设计模式.创建型模式.抽象工厂;

/**
 * @description: do demo
 * @author: Azure
 * @date: 2024/8/28 周三 0:10
 * @Version 1.0
 **/

import com.example.设计模式.创建型模式.抽象工厂.app.Application;
import com.example.设计模式.创建型模式.抽象工厂.factories.GUIFactory;
import com.example.设计模式.创建型模式.抽象工厂.factories.MacOSFactory;
import com.example.设计模式.创建型模式.抽象工厂.factories.WindowsFactory;

public class Demo {

    /**
     * 应用程序选择工厂类型并在运行时（通常在初始化阶段）创建它，具体取决于配置或环境变量。
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
