package com.example.设计模式.行为设计模式.备忘录.commands;

/**
 * @description: 修改已选形状的颜色
 * @author: Azure
 * @date: 2024/8/28 周三 22:25
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.备忘录.editor.Editor;
import com.example.设计模式.行为设计模式.备忘录.shapes.Shape;

import java.awt.*;

public class ColorCommand implements Command {
    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color) {
        this.editor = editor;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Colorize: " + color.toString();
    }

    @Override
    public void execute() {
        for (Shape child : editor.getShapes().getSelected()) {
            child.setColor(color);
        }
    }
}
