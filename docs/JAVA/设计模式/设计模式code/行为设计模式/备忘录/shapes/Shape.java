package com.example.设计模式.行为设计模式.备忘录.shapes;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 22:25
 * @Version 1.0
 **/

import java.awt.*;
import java.io.Serializable;

public interface Shape extends Serializable {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void drag();
    void drop();
    void moveTo(int x, int y);
    void moveBy(int x, int y);
    boolean isInsideBounds(int x, int y);
    Color getColor();
    void setColor(Color color);
    void select();
    void unSelect();
    boolean isSelected();
    void paint(Graphics graphics);
}
