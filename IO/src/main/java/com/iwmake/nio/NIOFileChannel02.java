package com.iwmake.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Dylan
 * @since 2020-10-26
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {
        File file = new File("file01.txt");
        FileInputStream in = new FileInputStream(file);

        FileChannel channel = in.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 将通道数据读入buffer
        channel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        in.close();
    }
}
