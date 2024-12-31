package com.example.设计模式.行为设计模式.模板方法模式;

import com.example.设计模式.行为设计模式.模板方法模式.networks.Facebook;
import com.example.设计模式.行为设计模式.模板方法模式.networks.Network;
import com.example.设计模式.行为设计模式.模板方法模式.networks.Twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 14:59
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("Input user name: ");
        String userName = reader.readLine();
        System.out.print("Input password: ");
        String password = reader.readLine();

        // Enter the message.
        System.out.print("Input message: ");
        String message = reader.readLine();

        System.out.println("\nChoose social network for posting message.\n" +
                "1 - Facebook\n" +
                "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());

        // 创建合适的网络对象并发送消息。
        if (choice == 1) {
            network = new Facebook(userName, password);
        } else if (choice == 2) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }
}
