package com.example.设计模式.行为设计模式.迭代器.social_networks;

import com.example.设计模式.行为设计模式.迭代器.iterators.ProfileIterator;

/**
 * @description: 定义通用的社交网络接口
 * @author: Azure
 * @date: 2024/8/28 周三 14:10
 * @Version 1.0
 **/
public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}
