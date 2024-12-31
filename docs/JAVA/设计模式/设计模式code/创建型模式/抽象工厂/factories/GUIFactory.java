package com.example.设计模式.创建型模式.抽象工厂.factories;

import com.example.设计模式.创建型模式.抽象工厂.buttons.Button;
import com.example.设计模式.创建型模式.抽象工厂.checkboxes.Checkbox;

/**
 * @description:抽象工厂了解所有（抽象的）产品类型。
 * @author: Azure
 * @date: 2024/8/28 周三 0:04
 * @Version 1.0
 **/
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
