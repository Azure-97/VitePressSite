package com.example.设计模式.结构型模式.外观.some_complex_media_library;

/**
 * @description: 混音器
 * @author: Azure
 * @date: 2024/8/29 周四 11:50
 * @Version 1.0
 **/
import java.io.File;

public class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("混音器：修复音频...");
        return new File("tmp");
    }
}
