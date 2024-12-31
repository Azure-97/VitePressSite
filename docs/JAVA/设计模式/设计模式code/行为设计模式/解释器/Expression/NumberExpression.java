package com.example.设计模式.行为设计模式.解释器.Expression;

import com.example.设计模式.行为设计模式.解释器.Expression.Expression;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/2 周一 23:35
 * @Version 1.0
 **/
public class NumberExpression implements Expression {

    private final long number;
    public NumberExpression(long number) {
        this.number = number;
    }
    public NumberExpression(String number) {
        this.number = Long.parseLong(number);
    }

    @Override
    public long interpret() {
        return this.number;
    }
}
