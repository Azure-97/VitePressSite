package com.example.设计模式.行为设计模式.备忘录.shapes;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 22:27
 * @Version 1.0
 **/
import java.awt.*;

public class Rectangle extends BaseShape {
    private int width;
    private int height;

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
