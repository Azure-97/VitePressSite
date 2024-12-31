package com.example.设计模式.行为设计模式.访问者.shapes;

/**
 * @description: 组合形状
 * @author: Azure
 * @date: 2024/8/28 周三 21:08
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.访问者.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {
    public int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void draw() {
        // draw shape
    }

    public int getId() {
        return id;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public void add(Shape shape) {
        children.add(shape);
    }
}
