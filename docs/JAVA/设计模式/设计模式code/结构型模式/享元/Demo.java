package com.example.设计模式.结构型模式.享元;

import com.example.设计模式.结构型模式.享元.forest.Forest;

import java.awt.*;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 14:01
 * @Version 1.0
 **/
public class Demo {
    static int CANVAS_SIZE = 500;//画布大小
    static int TREES_TO_DRAW = 1000000;//数
    static int TREE_TYPES = 2;//状态

    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "夏橡树", Color.GREEN, "橡木纹理存根");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "秋橡树", Color.ORANGE, "秋橡树纹理存根");
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);

        System.out.println("绘制了 "+TREES_TO_DRAW + " 棵树");
        System.out.println("---------------------");
        System.out.println("内存使用情况：");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
