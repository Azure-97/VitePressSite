package com.example.设计模式.结构型模式.装饰.decorators;

/**
 * @description: 抽象基础装饰
 * @author: Azure
 * @date: 2024/8/29 周四 10:53
 * @Version 1.0
 **/
public abstract class DataSourceDecorator implements DataSource {
    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
