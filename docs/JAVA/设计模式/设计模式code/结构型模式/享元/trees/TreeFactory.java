package com.example.设计模式.结构型模式.享元.trees;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 14:00
 * @Version 1.0
 **/
public class TreeFactory {
    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType result = treeTypes.get(name);
        if (result == null) {
            result = new TreeType(name, color, otherTreeData);
            treeTypes.put(name, result);
        }
        return result;
    }
}
