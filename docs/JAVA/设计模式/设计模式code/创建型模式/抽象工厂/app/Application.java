package com.example.设计模式.创建型模式.抽象工厂.app;

import com.example.设计模式.创建型模式.抽象工厂.buttons.Button;
import com.example.设计模式.创建型模式.抽象工厂.checkboxes.Checkbox;
import com.example.设计模式.创建型模式.抽象工厂.factories.GUIFactory;

/**
 * @description: Factory 用户并不关心他们使用哪个具体的工厂，因为他们通过<i>抽象接口</i>处理工厂和产品。
 * @author: Azure
 * @date: 2024/8/28 周三 0:09
 * @Version 1.0
 **/

public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
