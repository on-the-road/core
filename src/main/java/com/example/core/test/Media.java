package com.example.core.test;

import it.sauronsoftware.jave.Encoder;

import java.io.File;
import java.nio.channels.FileChannel;

/**
 * @Author wangwei
 * @Date 2019/7/29 18:13
 * -描述-
 */
public class Media {
    public static void main(String[] args) {
        File source = new File("./aa.mov");

        Encoder encoder = new Encoder();

        FileChannel fc= null;

        String size = "";

        try {

            it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(source);

            double ls = m.getDuration();

            System.out.println("此视频时长为:" + ls+ "毫秒！");

//视频帧宽高

            System.out.println("此视频高度为:" + m.getVideo().getSize().getHeight());

            System.out.println("此视频宽度为:" + m.getVideo().getSize().getWidth());

            System.out.println("此视频格式为:" + m.getFormat());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
