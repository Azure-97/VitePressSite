package com.example.设计模式.行为设计模式.解释器.Expression;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/2 周一 23:37
 * @Version 1.0
 **/
public class DivisionExpression implements Expression{

    private final Expression expression1;
    private final Expression expression2;
    public DivisionExpression(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    @Override
    public long interpret() {
        return this.expression1.interpret() / this.expression2.interpret();
    }
}
