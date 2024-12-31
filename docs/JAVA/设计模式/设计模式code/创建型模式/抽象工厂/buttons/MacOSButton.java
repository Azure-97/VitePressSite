package com.example.设计模式.创建型模式.抽象工厂.buttons;

/**
 * @description: 所有产品系列都有相同的品种 （MacOS/Windows）。这是按钮的 MacOS 变体。
 * @author: Azure
 * @date: 2024/8/27 周二 23:59
 * @Version 1.0
 **/
public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
