package com.example.设计模式.创建型模式.工厂方法.buttons;

/**
 * @description: HTML button implementation.
 * @author: Azure
 * @date: 2024/8/28 周三 9:13
 * @Version 1.0
 **/

public class HtmlButton implements Button {

    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
