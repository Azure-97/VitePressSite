package com.example.设计模式.行为设计模式.命令.commands;

import com.example.设计模式.行为设计模式.命令.editor.Editor;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 11:17
 * @Version 1.0
 **/
public abstract class Command {
    public Editor editor;
    private String backup;

    Command(Editor editor) {
        this.editor = editor;
    }

    void backup() {
        backup = editor.textField.getText();
    }

    public void undo() {
        editor.textField.setText(backup);
    }

    public abstract boolean execute();
}
