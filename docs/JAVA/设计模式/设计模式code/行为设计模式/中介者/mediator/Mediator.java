package com.example.设计模式.行为设计模式.中介者.mediator;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 17:35
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.中介者.components.Component;

import javax.swing.*;

/**
 * Common mediator interface.
 */
public interface Mediator {
    void addNewNote(Note note);
    void deleteNote();
    void getInfoFromList(Note note);
    void saveChanges();
    void markNote();
    void clear();
    void sendToFilter(ListModel listModel);
    void setElementsList(ListModel list);
    void registerComponent(Component component);
    void hideElements(boolean flag);
    void createGUI();
}
