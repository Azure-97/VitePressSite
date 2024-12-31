package com.example.设计模式.结构型模式.装饰.decorators;

/**
 * @description: 定义了读取和写入操作的通用数据接口
 * @author: Azure
 * @date: 2024/8/29 周四 10:51
 * @Version 1.0
 **/
public interface DataSource {
    void writeData(String data);

    String readData();
}
