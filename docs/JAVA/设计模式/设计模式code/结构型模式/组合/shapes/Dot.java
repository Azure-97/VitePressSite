package com.example.设计模式.结构型模式.组合.shapes;
import java.awt.*;
/**
 * @description: 点
 * @author: Azure
 * @date: 2024/8/27 周二 16:46
 * @Version 1.0
 **/
public class Dot extends BaseShape {
    private final int DOT_SIZE = 3;

    public Dot(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public int getWidth() {
        return DOT_SIZE;
    }

    @Override
    public int getHeight() {
        return DOT_SIZE;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.fillRect(x - 1, y - 1, getWidth(), getHeight());
    }
}
