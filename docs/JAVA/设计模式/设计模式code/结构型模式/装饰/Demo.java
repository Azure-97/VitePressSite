package com.example.设计模式.结构型模式.装饰;

import com.example.设计模式.结构型模式.装饰.decorators.*;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 10:41
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {

        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";

        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);

        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input 原文 ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded 加密 --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded 解密 --------------");
        System.out.println(encoded.readData());
    }
}
