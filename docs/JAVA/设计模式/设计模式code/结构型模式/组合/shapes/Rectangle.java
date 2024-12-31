package com.example.设计模式.结构型模式.组合.shapes;
import java.awt.*;
/**
 * @description: 三角形
 * @author: Azure
 * @date: 2024/8/27 周二 16:48
 * @Version 1.0
 **/
public class Rectangle extends BaseShape {
    public int width;
    public int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawRect(x, y, getWidth() - 1, getHeight() - 1);
    }
}
