package com.example.设计模式.行为设计模式.迭代器.social_networks;

import com.example.设计模式.行为设计模式.迭代器.iterators.FacebookIterator;
import com.example.设计模式.行为设计模式.迭代器.iterators.ProfileIterator;
import com.example.设计模式.行为设计模式.迭代器.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 在 Facebook 档案上实现迭代
 * @author: Azure
 * @date: 2024/8/28 周三 14:10
 * @Version 1.0
 **/
public class Facebook implements SocialNetwork {
    private List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        if (cache != null) {
            this.profiles = cache;
        } else {
            this.profiles = new ArrayList<>();
        }
    }

    public Profile requestProfileFromFacebook(String profileEmail) {
        // 这将是一个POST请求到Facebook API端点之一。
        // 我们模拟长网络连接，模拟现实生活。
        simulateNetworkLatency();
        System.out.println("Facebook: 正在通过网络加载 '" + profileEmail + "' 的资料");

        // 返回测试数据
        return findProfile(profileEmail);
    }

    public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
        // 这将是一个POST请求到Facebook API端点之一。
        // 我们模拟长网络连接，模拟现实生活。
        simulateNetworkLatency();
        System.out.println("Facebook:正在通过网络加载 '" + profileEmail + "' 的 '" + contactType + "' 信息");

        // 返回测试数据
        Profile profile = findProfile(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : profiles) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }

}
