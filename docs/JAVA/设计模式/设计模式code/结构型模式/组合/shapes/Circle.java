package com.example.设计模式.结构型模式.组合.shapes;
import java.awt.*;
/**
 * @description: 圆形
 * @author: Azure
 * @date: 2024/8/27 周二 16:47
 * @Version 1.0
 **/
public class Circle extends BaseShape {
    public int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawOval(x, y, getWidth() - 1, getHeight() - 1);
    }
}
