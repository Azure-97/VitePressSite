package com.example.设计模式.行为设计模式.观察者模式.listeners;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 11:15
 * @Version 1.0
 **/
import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}
