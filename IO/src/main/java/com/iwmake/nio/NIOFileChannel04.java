package com.iwmake.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 使用transferFrom拷贝文件
 * @author Dylan
 * @since 2020-10-26
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("1.txt");
        FileOutputStream fos = new FileOutputStream("4.txt");

        FileChannel source = fis.getChannel();
        FileChannel des = fos.getChannel();

        // transferFrom拷贝文件
        des.transferFrom(source, 0, source.size());

        source.close();
        des.close();
        fis.close();
        fos.close();
    }
}
