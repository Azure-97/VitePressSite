package com.example.设计模式.行为设计模式.状态模式.states;

import com.example.设计模式.行为设计模式.状态模式.ui.Player;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 15:15
 * @Version 1.0
 **/
public class LockedState extends State {

    LockedState(Player player) {
        super(player);
        player.setPlaying(false);
    }

    @Override
    public String onLock() {
        if (player.isPlaying()) {
            player.changeState(new ReadyState(player));
            return "Stop playing";
        } else {
            return "Locked...";
        }
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Ready";
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
