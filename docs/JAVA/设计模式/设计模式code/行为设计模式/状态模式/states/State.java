package com.example.设计模式.行为设计模式.状态模式.states;



import com.example.设计模式.行为设计模式.状态模式.ui.Player;

/**
 * @description: 所有状态的公共接口。
 * @author: Azure
 * @date: 2024/8/27 周二 15:15
 * @Version 1.0
 **/

public abstract class State {
    Player player;

/**Context通过状态构造函数传递自身。如果需要，这可以帮助状态获取一些有用的上下文数据。**/
    State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
}
