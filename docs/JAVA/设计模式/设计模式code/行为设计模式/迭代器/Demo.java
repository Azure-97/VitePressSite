package com.example.设计模式.行为设计模式.迭代器;

/**
 * @description: Demo class. Everything comes together here.
 * @author: Azure
 * @date: 2024/8/28 周三 14:22
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.迭代器.profile.Profile;
import com.example.设计模式.行为设计模式.迭代器.social_networks.Facebook;
import com.example.设计模式.行为设计模式.迭代器.social_networks.LinkedIn;
import com.example.设计模式.行为设计模式.迭代器.social_networks.SocialNetwork;
import com.example.设计模式.行为设计模式.迭代器.spammer.SocialSpammer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Demo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please specify social network to target spam tool (default:Facebook):");
        System.out.println("1. Facebook");
        System.out.println("2. LinkedIn");
        String choice = scanner.nextLine();

        SocialNetwork network;
        if (choice.equals("2")) {
            network = new LinkedIn(createTestProfiles());
        }
        else {
            network = new Facebook(createTestProfiles());
        }

        SocialSpammer spammer = new SocialSpammer(network);
        System.out.println("======== 发送消息给朋友 ==========");
        spammer.sendSpamToFriends("anna.smith@bing.com",
                "Hey! This is Anna's friend Josh. Can you do me a favor and like this post [link]?");
        System.out.println("======== 发送消息给同事 ==========");
        spammer.sendSpamToCoworkers("anna.smith@bing.com",
                "Hey! This is Anna's boss Jason. Anna told me you would be interested in [link].");
    }

    public static List<Profile> createTestProfiles() {
        List<Profile> data = new ArrayList<Profile>();
        data.add(new Profile("anna.smith@bing.com", "Anna Smith", "friends:mad_max@ya.com", "friends:catwoman@yahoo.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("mad_max@ya.com", "Maximilian", "friends:anna.smith@bing.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("bill@microsoft.eu", "Billie", "coworkers:avanger@ukr.net"));
        data.add(new Profile("avanger@ukr.net", "John Day", "coworkers:bill@microsoft.eu"));
        data.add(new Profile("sam@amazon.com", "Sam Kitting", "coworkers:anna.smith@bing.com", "coworkers:mad_max@ya.com", "friends:catwoman@yahoo.com"));
        data.add(new Profile("catwoman@yahoo.com", "Liza", "friends:anna.smith@bing.com", "friends:sam@amazon.com"));
        return data;
    }
}
