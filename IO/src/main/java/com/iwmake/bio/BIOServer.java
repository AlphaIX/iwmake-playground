package com.iwmake.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dylan
 * @since 2020-10-26
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // -------------------------------------
        // BIO缺点，每连接一个线程，可使用线程池复用线程
        // server accept阻塞
        // client read阻塞
        // -------------------------------------
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务器启动了！");

        while (true) {
            System.out.println("accept......");
            final Socket client = server.accept();// 阻塞
            System.out.println("接受了一个客户端");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(client);
                }
            });
        }
    }

    public static void handler(Socket client) {
        System.out.println("当前线程:"+Thread.currentThread().getName() + " id:"+ Thread.currentThread().getId());
        byte[] bytes = new byte[1024];
        // 通过socket获取一个输入流
        try {
            InputStream in = client.getInputStream();

            // 读取数据
            while (true) {
                System.out.println("read.......");
                int read = in.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0 , read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭client连接");
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
