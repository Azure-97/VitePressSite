package com.example.设计模式.行为设计模式.策略模式.order;



import com.example.设计模式.行为设计模式.策略模式.strategies.PayStrategy;

/**
 * @description:
 * 订单类。不知道用户选择的具体支付方式(策略)。
 * 它使用公共策略接口将收集支付数据委托给策略对象。
 * 它可以用于将订单保存到数据库中。
 * @author: Azure
 * @date: 2024/8/27 周二 14:05
 * @Version 1.0
 **/
public class Order {
    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}
