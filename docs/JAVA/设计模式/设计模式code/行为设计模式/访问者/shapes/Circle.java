package com.example.设计模式.行为设计模式.访问者.shapes;

import com.example.设计模式.行为设计模式.访问者.visitor.Visitor;

/**
 * @description: 圆形
 * @author: Azure
 * @date: 2024/8/28 周三 21:07
 * @Version 1.0
 **/

public class Circle extends Dot {
    private int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }
}
