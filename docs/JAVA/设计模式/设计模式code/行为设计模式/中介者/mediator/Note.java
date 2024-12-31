package com.example.设计模式.行为设计模式.中介者.mediator;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 17:36
 * @Version 1.0
 **/

/**
 * Note class.
 */
public class Note {
    private String name;
    private String text;

    public Note() {
        name = "New note";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}
