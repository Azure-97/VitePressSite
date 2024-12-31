package com.example.设计模式.行为设计模式.迭代器.social_networks;

import com.example.设计模式.行为设计模式.迭代器.iterators.LinkedInIterator;
import com.example.设计模式.行为设计模式.迭代器.iterators.ProfileIterator;
import com.example.设计模式.行为设计模式.迭代器.profile.Profile;

import java.util.ArrayList;
import java.util.List;
/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 14:10
 * @Version 1.0
 **/

public class LinkedIn implements SocialNetwork {
    private List<Profile> contacts;

    public LinkedIn(List<Profile> cache) {
        if (cache != null) {
            this.contacts = cache;
        } else {
            this.contacts = new ArrayList<>();
        }
    }
    /**
     * @Description 从 Linked In API 请求联系信息
     **/
    public Profile requestContactInfoFromLinkedInAPI(String profileEmail) {
        // 这将是一个POST请求到LinkedIn API端点之一。
        // 我们模拟长网络连接，模拟现实生活。
        simulateNetworkLatency();
        System.out.println("LinkedIn: 正在通过网络加载 '" + profileEmail + "' 的资料");

        // 返回测试数据
        return findContact(profileEmail);
    }

    public List<String> requestRelatedContactsFromLinkedInAPI(String profileEmail, String contactType) {
        // 这将是一个POST请求到LinkedIn API端点之一。
        // 我们模拟长网络连接，模拟现实生活。
        simulateNetworkLatency();
        System.out.println("LinkedIn: 正在通过网络加载 '" + profileEmail + "' 的 '" + contactType + "' 信息");

        // 返回测试数据
        Profile profile = findContact(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findContact(String profileEmail) {
        for (Profile profile : contacts) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }


    /**
     * @Description 模拟网络延迟
     **/
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new LinkedInIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new LinkedInIterator(this, "coworkers", profileEmail);
    }
}
