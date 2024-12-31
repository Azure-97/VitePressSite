package com.example.设计模式.结构型模式.组合;

import com.example.设计模式.结构型模式.组合.editor.ImageEditor;
import com.example.设计模式.结构型模式.组合.shapes.Circle;
import com.example.设计模式.结构型模式.组合.shapes.CompoundShape;
import com.example.设计模式.结构型模式.组合.shapes.Dot;
import com.example.设计模式.结构型模式.组合.shapes.Rectangle;

import java.awt.*;


/**
 * @description: 本例展示了如何利用较为简单的形状来组成复杂图形， 以及如何统一处理简单和复杂图形。
 * @author: Azure
 * @date: 2024/8/27 周二 16:51
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

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
