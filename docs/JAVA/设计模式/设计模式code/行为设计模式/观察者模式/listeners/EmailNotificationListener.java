package com.example.设计模式.行为设计模式.观察者模式.listeners;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 11:16
 * @Version 1.0
 **/
import java.io.File;

public class EmailNotificationListener implements EventListener {
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
