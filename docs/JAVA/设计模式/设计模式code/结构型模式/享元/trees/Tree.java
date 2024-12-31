package com.example.设计模式.结构型模式.享元.trees;

import java.awt.*;
/**
 * @description: 包含每棵树的独特状态
 * @author: Azure
 * @date: 2024/8/29 周四 13:59
 * @Version 1.0
 **/

public class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}
