package com.example.设计模式.结构型模式.桥接.devices;

/**
 * @description: 所有设备的通用接口
 * @author: Azure
 * @date: 2024/8/29 周四 14:28
 * @Version 1.0
 **/
public interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
