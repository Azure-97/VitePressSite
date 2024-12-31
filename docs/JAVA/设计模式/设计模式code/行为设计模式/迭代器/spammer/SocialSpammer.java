package com.example.设计模式.行为设计模式.迭代器.spammer;

import com.example.设计模式.行为设计模式.迭代器.iterators.ProfileIterator;
import com.example.设计模式.行为设计模式.迭代器.profile.Profile;
import com.example.设计模式.行为设计模式.迭代器.social_networks.SocialNetwork;

/**
 * @description: 消息发送应用
 * @author: Azure
 * @date: 2024/8/28 周三 14:11
 * @Version 1.0
 **/

public class SocialSpammer {
    public SocialNetwork network;
    public ProfileIterator iterator;

    public SocialSpammer(SocialNetwork network) {
        this.network = network;
    }

    public void sendSpamToFriends(String profileEmail, String message) {
        System.out.println("\n正在迭代朋友...\n");
        iterator = network.createFriendsIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendSpamToCoworkers(String profileEmail, String message) {
        System.out.println("\n正在迭代同事...\n");
        iterator = network.createCoworkersIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendMessage(String email, String message) {
        System.out.println("发送消息至：'" + email + "'. 消息正文： '" + message + "'");
    }
}
