package com.example.设计模式.结构型模式.代理.downloader;

import com.example.设计模式.结构型模式.代理.some_cool_media_library.ThirdPartyYouTubeLib;
import com.example.设计模式.结构型模式.代理.some_cool_media_library.Video;

import java.util.HashMap;

/**
 * @description: 媒体下载应用
 * @author: Azure
 * @date: 2024/8/29 周四 17:43
 * @Version 1.0
 **/
public class YouTubeDownloader {
    private ThirdPartyYouTubeLib api;

    public YouTubeDownloader(ThirdPartyYouTubeLib api) {
        this.api = api;
    }
/**
 * @Description 通过videoId获取
 **/
    public void renderVideoPage(String videoId) {
        Video video = api.getVideo(videoId);
        System.out.println("\n-------------------------------");
        System.out.println("Video page (imagine fancy HTML)");
        System.out.println("ID: " + video.id);
        System.out.println("Title: " + video.title);
        System.out.println("Video: " + video.data);
        System.out.println("-------------------------------\n");
    }

    /**
     * @Description 从流行缓存中获取
     **/
    public void renderPopularVideos() {
        HashMap<String, Video> list = api.popularVideos();
        System.out.println("\n-------------------------------");
        System.out.println("Most popular videos on YouTube (imagine fancy HTML)");
        for (Video video : list.values()) {
            System.out.println("ID: " + video.id + " / Title: " + video.title);
        }
        System.out.println("-------------------------------\n");
    }
}
