package com.example.设计模式.结构型模式.享元.forest;

import com.example.设计模式.结构型模式.享元.trees.Tree;
import com.example.设计模式.结构型模式.享元.trees.TreeFactory;
import com.example.设计模式.结构型模式.享元.trees.TreeType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 14:00
 * @Version 1.0
 **/


public class Forest extends JFrame {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }
}
