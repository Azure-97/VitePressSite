package com.example.设计模式.行为设计模式.访问者.shapes;

import com.example.设计模式.行为设计模式.访问者.visitor.Visitor;

/**
 * @description: 通用形状接口
 * @author: Azure
 * @date: 2024/8/28 周三 21:06
 * @Version 1.0
 **/

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
