package com.example.设计模式.行为设计模式.中介者;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 17:39
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.中介者.components.*;
import com.example.设计模式.行为设计模式.中介者.mediator.Editor;
import com.example.设计模式.行为设计模式.中介者.mediator.Mediator;

import javax.swing.*;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
