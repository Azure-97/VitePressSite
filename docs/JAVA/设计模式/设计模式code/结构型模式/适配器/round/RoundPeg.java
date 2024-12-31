package com.example.设计模式.结构型模式.适配器.round;

/**
 * @description: 圆钉
 * @author: Azure
 * @date: 2024/8/27 周二 15:57
 * @Version 1.0
 **/
public class RoundPeg {
    private double radius;

    public RoundPeg() {}

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
