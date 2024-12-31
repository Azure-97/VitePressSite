package com.example.设计模式.结构型模式.代理.some_cool_media_library;

/**
 * @description: 视频文件
 * @author: Azure
 * @date: 2024/8/29 周四 17:43
 * @Version 1.0
 **/
public class Video {
    public String id;
    public String title;
    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}
