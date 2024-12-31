package com.example.设计模式.行为设计模式.迭代器.iterators;

import com.example.设计模式.行为设计模式.迭代器.profile.Profile;

/**
 * @description: 定义档案接口
 * @author: Azure
 * @date: 2024/8/28 周三 14:09
 * @Version 1.0
 **/
public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}
