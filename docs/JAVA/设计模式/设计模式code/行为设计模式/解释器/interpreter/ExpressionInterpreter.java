package com.example.设计模式.行为设计模式.解释器.interpreter;

import com.example.设计模式.行为设计模式.解释器.Expression.*;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/2 周一 23:36
 * @Version 1.0
 **/
public class ExpressionInterpreter {

    private final Deque<Expression> numbers = new LinkedList<>();

    public long interpret(String expression) {
        String[] element = expression.split(" ");
        int length = element.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            numbers.add(new NumberExpression(element[i]));
        }

        for (int i = (length + 1) / 2; i < length; i++) {
            String operator = element[i];
            boolean isValid = "+".equals(operator) || "-".equals(operator)
                    || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            Expression e1 = numbers.pollFirst();
            Expression e2 = numbers.pollFirst();
            Expression result = null;
            if ("+".equals(operator)) {
                result = new AdditionExpression(e1, e2);
            } else if ("-".equals(operator)) {
                result = new SubtractionExpression(e1, e2);
            } else if ("*".equals(operator)) {
                result = new MultiplicationExpression(e1, e2);
            } else if ("/".equals(operator)) {
                result = new DivisionExpression(e1, e2);
            }
            numbers.addFirst(new NumberExpression(result.interpret()));
        }
        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }
        return numbers.pop().interpret();
    }
}
