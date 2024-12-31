package com.example.设计模式.行为设计模式.观察者模式.listeners;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 11:16
 * @Version 1.0
 **/

import java.io.File;

public class LogOpenListener implements EventListener {
    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log
                + ": Someone has performed " + eventType
                + " operation with the following file: " + file.getName());
    }
}
