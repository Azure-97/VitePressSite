package com.example.设计模式.行为设计模式.备忘录.commands;

/**
 * @description: 基础命令类
 * @author: Azure
 * @date: 2024/8/28 周三 22:24
 * @Version 1.0
 **/
public interface Command {
    String getName();
    void execute();
}
