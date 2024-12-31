package com.example.设计模式.行为设计模式.观察者模式;

import com.example.设计模式.行为设计模式.观察者模式.editor.Editor;
import com.example.设计模式.行为设计模式.观察者模式.listeners.EmailNotificationListener;
import com.example.设计模式.行为设计模式.观察者模式.listeners.LogOpenListener;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 11:16
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    @Result:
    Save to log \path\to\log\file.txt: Someone has performed open operation with the following file: test.txt
    Email to admin@example.com: Someone has performed save operation with the following file: test.txt
     **/
}
