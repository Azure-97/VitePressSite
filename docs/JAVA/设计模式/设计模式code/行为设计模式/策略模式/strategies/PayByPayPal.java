package com.example.设计模式.行为设计模式.策略模式.strategies;

/**
 * @description: 使用 PayPal 支付
 * @author: Azure
 * @date: 2024/8/27 周二 14:03
 * @Version 1.0
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PayByPayPal implements PayStrategy {
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("amanda1985", "amanda@ya.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }

    /**
     * 收集客户的数据。
     */
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.print("输入用户的电子邮件地址: ");
                email = READER.readLine();
                System.out.print("输入密码: ");
                password = READER.readLine();
                if (verify()) {
                    System.out.println("数据验证已成功。");
                } else {
                    System.out.println("电子邮件或密码错误!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * 保存客户数据以备将来购物时使用。
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("使用 PayPal支付￥" + paymentAmount + ".");
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
