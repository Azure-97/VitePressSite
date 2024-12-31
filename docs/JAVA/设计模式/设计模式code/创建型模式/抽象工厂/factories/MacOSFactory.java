package com.example.设计模式.创建型模式.抽象工厂.factories;

import com.example.设计模式.创建型模式.抽象工厂.buttons.Button;
import com.example.设计模式.创建型模式.抽象工厂.buttons.MacOSButton;
import com.example.设计模式.创建型模式.抽象工厂.checkboxes.Checkbox;
import com.example.设计模式.创建型模式.抽象工厂.checkboxes.MacOSCheckbox;

/**
 * @description: 每个工厂都扩展了基本工厂，并负责生产单一品种的产品。
 * @author: Azure
 * @date: 2024/8/28 周三 0:05
 * @Version 1.0
 **/

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
