package com.example.设计模式.行为设计模式.观察者模式.editor;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 11:13
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.观察者模式.publisher.EventManager;

import java.io.File;

public class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }
}
