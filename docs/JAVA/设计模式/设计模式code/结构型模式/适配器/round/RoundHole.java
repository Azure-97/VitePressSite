package com.example.设计模式.结构型模式.适配器.round;

/**
 * @description : 圆孔
 * @author: Azure
 * @date: 2024/8/27 周二 15:57
 * @Version 1.0
 **/
public class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        boolean result;
        result = (this.getRadius() >= peg.getRadius());
        return result;
    }
}
