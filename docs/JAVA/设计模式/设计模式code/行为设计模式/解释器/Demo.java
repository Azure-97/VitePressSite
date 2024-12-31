package com.example.设计模式.行为设计模式.解释器;

import com.example.设计模式.行为设计模式.解释器.interpreter.ExpressionInterpreter;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/2 周一 23:38
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        /*
        1 8-3=5 -> 5 2 4 + *
        2 5+2=7 -> 7 4 *
        3 7*4=28
         */
        String expression = "8 3 2 4 - + *";
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        long number = expressionInterpreter.interpret(expression);
        System.out.println(number);
    }
}
