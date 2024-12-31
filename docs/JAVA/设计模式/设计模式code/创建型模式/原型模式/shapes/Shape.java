package com.example.设计模式.创建型模式.原型模式.shapes;

import java.util.Objects;
/**
 * @description:  通用形状接口
 * @author: Azure
 * @date: 2024/8/27 周二 17:11
 * @Version 1.0
 **/
public abstract class Shape {
    public int x;
    public int y;
    public String color;

    public Shape() {
    }

    public Shape(Shape target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Shape)) return false;
        Shape shape2 = (Shape) object2;
        return shape2.x == x && shape2.y == y && Objects.equals(shape2.color, color);
    }
}
