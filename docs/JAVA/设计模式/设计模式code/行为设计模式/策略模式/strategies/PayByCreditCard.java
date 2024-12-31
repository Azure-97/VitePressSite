package com.example.设计模式.行为设计模式.策略模式.strategies;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @description: TODO 具体的策略。实现信用卡支付方式。
 * @author: Azure
 * @date: 2024/8/27 周二 14:04
 * @Version 1.0
 **/
public class PayByCreditCard implements PayStrategy {
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    /**
     * 收集信用卡数据。
     */
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("输入卡号: ");
            String number = READER.readLine();
            System.out.print("输入卡到期日期 'mm/yy'： ");
            String date = READER.readLine();
            System.out.print("输入 CVV 代码： ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

            // Validate credit card number...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 卡验证后，我们可以收取客户的信用卡。
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("使用信用卡支付：￥" + paymentAmount + "。");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}
