package com.example.设计模式.创建型模式.原型模式.shapes;

/**
 * @description: 简单形状
 * @author: Azure
 * @date: 2024/8/27 周二 17:12
 * @Version 1.0
 **/
public class Circle extends Shape {
    public int radius;

    public Circle() {
    }

    public Circle(Circle target) {
        super(target);
        if (target != null) {
            this.radius = target.radius;
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Circle) || !super.equals(object2)) return false;
        Circle shape2 = (Circle) object2;
        return shape2.radius == radius;
    }
}
