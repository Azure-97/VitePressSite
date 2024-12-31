package com.example.设计模式.结构型模式.外观.facade;

import com.example.设计模式.结构型模式.外观.some_complex_media_library.*;

import java.io.File;

/**
 * @description:  外观提供了进行视频转换的简单接口
 * @author: Azure
 * @date: 2024/8/29 周四 11:45
 * @Version 1.0
 **/
public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade：转换已启动.");
        VideoFile file = new VideoFile(fileName);
        //提取
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if ("mp4".equals(format)) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade：转换完成。");
        return result;
    }
}
