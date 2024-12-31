package com.example.设计模式.结构型模式.组合.shapes;

import java.awt.*;

/**
 * @description: 通用形状接口
 * @author: Azure
 * @date: 2024/8/27 周二 16:45
 * @Version 1.0
 **/
public interface Shape {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void move(int x, int y);
    boolean isInsideBounds(int x, int y);
    void select();
    void unSelect();
    boolean isSelected();
    void paint(Graphics graphics);
}
