package com.example.设计模式.行为设计模式.状态模式;

/**
 * @description: TODO 演示
 * @author: Azure
 * @date: 2024/8/27 周二 15:19
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.状态模式.ui.Player;
import com.example.设计模式.行为设计模式.状态模式.ui.UI;

public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
