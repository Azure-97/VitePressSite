package com.example.设计模式.行为设计模式.备忘录;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 22:28
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.备忘录.editor.Editor;
import com.example.设计模式.行为设计模式.备忘录.shapes.Circle;
import com.example.设计模式.行为设计模式.备忘录.shapes.CompoundShape;
import com.example.设计模式.行为设计模式.备忘录.shapes.Dot;
import com.example.设计模式.行为设计模式.备忘录.shapes.Rectangle;

import java.awt.*;


public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}
