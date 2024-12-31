package com.example.设计模式.结构型模式.适配器.adapters;

import com.example.设计模式.结构型模式.适配器.round.RoundPeg;
import com.example.设计模式.结构型模式.适配器.square.SquarePeg;

/**
 * @description:  方钉到圆孔的适配器
 * @author: Azure
 * @date: 2024/8/27 周二 15:58
 * @Version 1.0
 **/
public class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        //计算一个最小的圆半径，它可以适合这个挂钩。
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }
}
