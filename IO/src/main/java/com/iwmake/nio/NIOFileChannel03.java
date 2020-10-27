package com.iwmake.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 拷贝文件
 *
 * @author Dylan
 * @since 2020-10-26
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("1.txt");
        FileChannel channel1 = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("2.txt");
        FileChannel channel2 = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            // 一定要先clear，重要！
            byteBuffer.clear();

            int read = channel1.read(byteBuffer);
            System.out.println("read:"+read);
            if(read == -1) {// 读完
                break;
            }

            // 将buffer数据写入channel2
            byteBuffer.flip();
            channel2.write(byteBuffer);
        }

        fis.close();
        fos.close();
    }
}
