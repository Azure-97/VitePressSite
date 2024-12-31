package com.example.设计模式.创建型模式.抽象工厂.checkboxes;

/**
 * @description: 所有产品系列都有相同的品种 （MacOS/Windows）。这是复选框的变体。
 * @author: Azure
 * @date: 2024/8/28 周三 0:02
 * @Version 1.0
 **/

public class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}
