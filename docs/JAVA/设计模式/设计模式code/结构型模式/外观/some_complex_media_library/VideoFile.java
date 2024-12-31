package com.example.设计模式.结构型模式.外观.some_complex_media_library;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 11:47
 * @Version 1.0
 **/
public class VideoFile {
    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() {
        return codecType;
    }

    public String getName() {
        return name;
    }
}
