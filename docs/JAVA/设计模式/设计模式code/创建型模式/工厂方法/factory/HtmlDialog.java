package com.example.设计模式.创建型模式.工厂方法.factory;

import com.example.设计模式.创建型模式.工厂方法.buttons.Button;
import com.example.设计模式.创建型模式.工厂方法.buttons.HtmlButton;

/**
 * @description: HTML Dialog will produce HTML buttons.
 * @author: Azure
 * @date: 2024/8/28 周三 9:24
 * @Version 1.0
 **/

public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
