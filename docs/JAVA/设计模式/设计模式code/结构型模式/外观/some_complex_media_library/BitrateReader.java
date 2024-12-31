package com.example.设计模式.结构型模式.外观.some_complex_media_library;

/**
 * @description: 比特率读取器
 * @author: Azure
 * @date: 2024/8/29 周四 11:49
 * @Version 1.0
 **/
public class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader：正在读取文件...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader：正在写入文件...");
        return buffer;
    }
}
