package com.example.设计模式.结构型模式.代理.proxy;

import com.example.设计模式.结构型模式.代理.some_cool_media_library.ThirdPartyYouTubeClass;
import com.example.设计模式.结构型模式.代理.some_cool_media_library.ThirdPartyYouTubeLib;
import com.example.设计模式.结构型模式.代理.some_cool_media_library.Video;

import java.util.HashMap;

/**
 * @description: 缓存代理
 * @author: Azure
 * @date: 2024/8/29 周四 17:43
 * @Version 1.0
 **/
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
    private HashMap<String, Video> cacheAll = new HashMap<String, Video>();

    public YouTubeCacheProxy() {
        this.youtubeService = new ThirdPartyYouTubeClass();
    }

    /**
     * @Description 从popular缓存中获取Videos
     **/
    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = youtubeService.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    /**
     * @Description 先查缓存过的，没有再从第三方获取
     **/
    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = youtubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cachePopular.clear();
        cacheAll.clear();
    }
}
