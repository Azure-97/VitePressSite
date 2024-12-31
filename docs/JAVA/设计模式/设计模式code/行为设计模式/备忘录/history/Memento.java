package com.example.设计模式.行为设计模式.备忘录.history;

import com.example.设计模式.行为设计模式.备忘录.editor.Editor;

/**
 * @description: 备忘录类
 * @author: Azure
 * @date: 2024/8/28 周三 22:24
 * @Version 1.0
 **/

public class Memento {
    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }
}
