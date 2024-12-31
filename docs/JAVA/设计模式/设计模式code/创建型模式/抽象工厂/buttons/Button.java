package com.example.设计模式.创建型模式.抽象工厂.buttons;

/**
 * @description: Abstract Factory 假定您有多个产品系列，它们被构建为单独的类层次结构 （Button/Checkbox）。同一系列的所有产品都有通用接口。这是按钮系列的通用接口。
 * @author: Azure
 * @date: 2024/8/27 周二 23:53
 * @Version 1.0
 **/
public interface Button {
    void paint();
}
