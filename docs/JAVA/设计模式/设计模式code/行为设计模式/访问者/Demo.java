package com.example.设计模式.行为设计模式.访问者;

import com.example.设计模式.行为设计模式.访问者.shapes.*;
import com.example.设计模式.行为设计模式.访问者.visitor.XMLExportVisitor;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 21:10
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        // 创建点
        Dot dot = new Dot(1, 10, 55);
        // 创建圆
        Circle circle = new Circle(2, 23, 15, 10);
        // 创建矩形
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);
        // 创建组合图形1
        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        // 创建组合图形2
        CompoundShape c = new CompoundShape(5);
        c.add(dot);
        compoundShape.add(c);
        // 导出
        export(circle, compoundShape);
    }
    // 访问者模式
    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }
}
