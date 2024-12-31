package com.example.设计模式.行为设计模式.命令.commands;

import com.example.设计模式.行为设计模式.命令.editor.Editor;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 11:30
 * @Version 1.0
 **/
public class PasteCommand extends Command {

    public PasteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if (editor.clipboard == null || editor.clipboard.isEmpty()) return false;

        backup();
        editor.textField.insert(editor.clipboard, editor.textField.getCaretPosition());
        return true;
    }
}
