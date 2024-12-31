package com.example.设计模式.行为设计模式.状态模式.states;


import com.example.设计模式.行为设计模式.状态模式.ui.Player;

/**
 * @description: 它们还可以在上下文中触发状态转换。
 * @author: Azure
 * @date: 2024/8/27 周二 15:16
 * @Version 1.0
 **/
public class ReadyState extends State {

    public ReadyState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        player.changeState(new LockedState(player));
        return "Locked...";
    }

    @Override
    public String onPlay() {
        String action = player.startPlayback();
        player.changeState(new PlayingState(player));
        return action;
    }

    @Override
    public String onNext() {
        return "Locked...";
    }

    @Override
    public String onPrevious() {
        return "Locked...";
    }
}
