package com.example.设计模式.创建型模式.工厂方法.factory;

import com.example.设计模式.创建型模式.工厂方法.buttons.Button;
import com.example.设计模式.创建型模式.工厂方法.buttons.WindowsButton;

/**
 * @description: Windows Dialog will produce Windows buttons.
 * @author: Azure
 * @date: 2024/8/28 周三 9:25
 * @Version 1.0
 **/

public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
