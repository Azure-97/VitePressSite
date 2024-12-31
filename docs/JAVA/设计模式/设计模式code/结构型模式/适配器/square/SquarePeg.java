package com.example.设计模式.结构型模式.适配器.square;

/**
 * @description: 方钉
 * @todo: SquarePeg 与 RoundHoles 不兼容（它们由以前的开发团队）。但我们必须将它们整合到我们的计划中。
 * @author: Azure
 * @date: 2024/8/27 周二 15:58
 * @Version 1.0
 **/
public class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        double result;
        result = Math.pow(this.width, 2);
        return result;
    }
}
