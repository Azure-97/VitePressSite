package com.example.设计模式.行为设计模式.中介者.components;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 17:34
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.中介者.mediator.Mediator;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
public class TextBox extends JTextArea implements Component {
    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        mediator.markNote();
    }

    @Override
    public String getName() {
        return "TextBox";
    }
}
