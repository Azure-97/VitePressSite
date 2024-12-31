package com.example.设计模式.行为设计模式.策略模式.strategies;

/**
 * @description: TODO 信用卡类
 * @author: Azure
 * @date: 2024/8/27 周二 14:05
 * @Version 1.0
 **/

public class CreditCard {
    private int amount;
    private String number;
    private String date;
    private String cvv;

    CreditCard(String number, String date, String cvv) {
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
