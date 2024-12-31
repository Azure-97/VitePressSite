package com.example.设计模式.行为设计模式.命令.commands;

import com.example.设计模式.行为设计模式.命令.editor.Editor;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 11:30
 * @Version 1.0
 **/
public class CutCommand extends Command {

    public CutCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if (editor.textField.getSelectedText().isEmpty()) return false;

        backup();
        String source = editor.textField.getText();
        editor.clipboard = editor.textField.getSelectedText();
        editor.textField.setText(cutString(source));
        return true;
    }

    private String cutString(String source) {
        String start = source.substring(0, editor.textField.getSelectionStart());
        String end = source.substring(editor.textField.getSelectionEnd());
        return start + end;
    }
}
