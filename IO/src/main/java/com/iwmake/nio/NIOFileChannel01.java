package com.iwmake.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Dylan
 * @since 2020-10-26
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {

        String str = "hello,你好";

        // 创建一个输出流->channel
        FileOutputStream fos = new FileOutputStream("file01.txt");

        // 通过输出流获取相应的channel FileChannelImpl
        FileChannel channel = fos.getChannel();

        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // str放入buffer
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        // 将byteBuffer写到channel
        channel.write(byteBuffer);
        fos.close();
    }
}
