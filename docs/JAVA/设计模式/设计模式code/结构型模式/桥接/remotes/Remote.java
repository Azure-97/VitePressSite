package com.example.设计模式.结构型模式.桥接.remotes;

/**
 * @description: 所有远程控制器的通用接口
 * @author: Azure
 * @date: 2024/8/29 周四 14:29
 * @Version 1.0
 **/
public interface Remote {
    void power();

    void volumeDown();

    void volumeUp();

    void channelDown();

    void channelUp();
}
