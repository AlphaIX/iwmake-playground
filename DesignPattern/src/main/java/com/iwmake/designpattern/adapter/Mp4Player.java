package com.iwmake.designpattern.adapter;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // 什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file.name: " + fileName);
    }
}
