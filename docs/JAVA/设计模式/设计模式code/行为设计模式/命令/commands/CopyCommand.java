package com.example.设计模式.行为设计模式.命令.commands;

import com.example.设计模式.行为设计模式.命令.editor.Editor;

/**
 * @description: 将所选文字复制到剪贴板
 * @author: Azure
 * @date: 2024/8/28 周三 11:29
 * @Version 1.0
 **/
public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}
