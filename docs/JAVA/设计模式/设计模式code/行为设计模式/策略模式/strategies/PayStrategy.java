package com.example.设计模式.行为设计模式.策略模式.strategies;

/**
 * @description: TODO 通用的支付方法接口
 * @author: Azure
 * @date: 2024/8/27 周二 14:03
 * @Version 1.0
 **/
public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
