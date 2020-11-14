package com.iwmake.framework.hadoop.hdfs.connect;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 使用URL方式访问数据（了解即可）
 * @author Dylan
 * @since 2020-11-14
 */
public class AccessByURL {

    @Test
    public void urlHDFS() throws IOException {
        // 1、注册URL
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        // 2、获取hdfs文件的输入流
        InputStream inputStream = new URL("hdfs://192.168.56.120:8020/a.txt").openStream();
        // 3、获取本地文件的输出流
        FileOutputStream outputStream = new FileOutputStream(new File("hello2.txt"));
        // 4、实现文件的拷贝
        IOUtils.copy(inputStream, outputStream);
        // 5、关闭流
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }
}
