package com.example.设计模式.结构型模式.代理.some_cool_media_library;

/**
 * @description: 远程服务接口
 * @author: Azure
 * @date: 2024/8/29 周四 17:42
 * @Version 1.0
 **/
import java.util.HashMap;

public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}
