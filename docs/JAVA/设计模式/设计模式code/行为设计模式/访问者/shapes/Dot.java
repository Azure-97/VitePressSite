package com.example.设计模式.行为设计模式.访问者.shapes;

import com.example.设计模式.行为设计模式.访问者.visitor.Visitor;

/**
 * @description: 点
 * @author: Azure
 * @date: 2024/8/28 周三 21:07
 * @Version 1.0
 **/

public class Dot implements Shape {
    private int id;
    private int x;
    private int y;

    public Dot() {
    }

    public Dot(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void draw() {
        // draw shape
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitDot(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
