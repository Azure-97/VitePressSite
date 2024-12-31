package com.example.设计模式.行为设计模式.策略模式;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 14:07
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.策略模式.order.Order;
import com.example.设计模式.行为设计模式.策略模式.strategies.PayByCreditCard;
import com.example.设计模式.行为设计模式.策略模式.strategies.PayByPayPal;
import com.example.设计模式.行为设计模式.策略模式.strategies.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 世界上第一个控制台电子商务应用程序。
 */
public class Demo {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("请选择一个产品:" + "\n" +
                        "1 - 主板" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - 硬盘" + "\n" +
                        "4 - 内存" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("您是否希望继续选择产品? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("请选择付款方式:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                //客户根据用户的输入创建不同的策略，
                //应用程序配置等
                if ("1".equals(paymentMethod)) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            /*
             Order对象将收集支付数据委托给策略对象，
             因为只有策略知道处理支付需要什么数据。
             */
            order.processOrder(strategy);

            System.out.print("支付 " + order.getTotalCost() + "(元)[P] 或 继续购物[C]? P/C: ");
            String proceed = reader.readLine();
            if ("P".equalsIgnoreCase(proceed)) {
                // 最后，strategy 处理付款。
                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("付款已成功。");
                } else {
                    System.out.println("失败！请检查您的数据。");
                }
                order.setClosed();
            }
        }
    }
}
