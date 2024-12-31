package com.example.设计模式.结构型模式.外观.some_complex_media_library;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/29 周四 11:48
 * @Version 1.0
 **/

public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory：提取 mpeg 音频...");
            return new MPEG4CompressionCodec();
        }
        else {
            System.out.println("CodecFactory：正在提取 ogg 音频...");
            return new OggCompressionCodec();
        }
    }
}
