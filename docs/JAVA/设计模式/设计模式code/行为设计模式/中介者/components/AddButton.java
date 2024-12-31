package com.example.设计模式.行为设计模式.中介者.components;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 15:37
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.中介者.mediator.Mediator;
import com.example.设计模式.行为设计模式.中介者.mediator.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
public class AddButton extends JButton implements Component {
    private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }
}
