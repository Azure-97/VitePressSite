package com.example.设计模式.创建型模式.工厂方法.factory;

import com.example.设计模式.创建型模式.工厂方法.buttons.Button;

/**
 * @description:Base factory class. Note that "factory" is merely a role for the class.
 * <p>It should have some core business logic which needs different products to be created.</p>
 * @author: Azure
 * @date: 2024/8/28 周三 9:20
 * @Version 1.0
 **/

public abstract class Dialog {

    public void renderWindow() {
        // ... other code ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Button createButton();
}
