package com.example.设计模式.行为设计模式.中介者.components;

import com.example.设计模式.行为设计模式.中介者.mediator.Mediator;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 15:37
 * @Version 1.0
 **/
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
