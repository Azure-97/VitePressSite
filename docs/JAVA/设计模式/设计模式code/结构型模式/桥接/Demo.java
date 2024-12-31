package com.example.设计模式.结构型模式.桥接;

import com.example.设计模式.结构型模式.桥接.devices.Device;
import com.example.设计模式.结构型模式.桥接.devices.Radio;
import com.example.设计模式.结构型模式.桥接.devices.Tv;
import com.example.设计模式.结构型模式.桥接.remotes.AdvancedRemote;
import com.example.设计模式.结构型模式.桥接.remotes.BasicRemote;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 14:34
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
