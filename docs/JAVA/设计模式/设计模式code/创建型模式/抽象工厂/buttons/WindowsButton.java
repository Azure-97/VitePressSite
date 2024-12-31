package com.example.设计模式.创建型模式.抽象工厂.buttons;

/**
 * @description: 所有产品系列都有相同的品种 （MacOS/Windows）。这是按钮的另一种变体。
 * @author: Azure
 * @date: 2024/8/28 周三 0:00
 * @Version 1.0
 **/
public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}
