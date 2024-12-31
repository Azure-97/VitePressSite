package com.example.设计模式.行为设计模式.命令.commands;
import java.util.Stack;
/**
 * @description: 命令历史
 * @author: Azure
 * @date: 2024/8/28 周三 11:31
 * @Version 1.0
 **/
public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }
}
