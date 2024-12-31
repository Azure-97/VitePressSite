package com.example.设计模式.行为设计模式.状态模式.states;

import com.example.设计模式.行为设计模式.状态模式.ui.Player;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 15:16
 * @Version 1.0
 **/
public class PlayingState extends State {

    PlayingState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        player.changeState(new LockedState(player));
        player.setCurrentTrackAfterStop();
        return "Stop playing";
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Paused...";
    }

    @Override
    public String onNext() {
        return player.nextTrack();
    }

    @Override
    public String onPrevious() {
        return player.previousTrack();
    }
}
