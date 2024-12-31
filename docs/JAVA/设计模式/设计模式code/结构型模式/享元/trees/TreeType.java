package com.example.设计模式.结构型模式.享元.trees;

import java.awt.*;
/**
 * @description: 包含多棵树共享的状态
 * @author: Azure
 * @date: 2024/8/29 周四 13:59
 * @Version 1.0
 **/
public class TreeType {
    private String name;
    private Color color;
    private String otherTreeData;

    public TreeType(String name, Color color, String otherTreeData) {
        this.name = name;
        this.color = color;
        this.otherTreeData = otherTreeData;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}
