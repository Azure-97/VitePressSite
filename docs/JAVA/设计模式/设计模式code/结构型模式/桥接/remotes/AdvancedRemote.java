package com.example.设计模式.结构型模式.桥接.remotes;

import com.example.设计模式.结构型模式.桥接.devices.Device;

/**
 * @description: 高级远程控制器
 * @author: Azure
 * @date: 2024/8/29 周四 14:29
 * @Version 1.0
 **/
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super.device = device;
    }
    // 静音
    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
