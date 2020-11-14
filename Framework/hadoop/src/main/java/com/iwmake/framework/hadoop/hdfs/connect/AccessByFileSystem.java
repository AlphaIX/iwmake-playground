package com.iwmake.framework.hadoop.hdfs.connect;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * 通过文件系统访问数据（推荐方式）
 * @author Dylan
 * @since 2020-11-14
 */
public class AccessByFileSystem {

    /**
     * 第一种方式
     */
    @Test
    public void getFileSystem1() throws IOException {
        // 1、创建Configuration对象
        Configuration configuration = new Configuration();

        // 2、设置文件系统类型
        configuration.set("fs.defaultFS", "hdfs://192.168.56.120:8020");

        // 3、获取指定文件系统
        FileSystem fileSystem = FileSystem.get(configuration);

        // 4、输出
        System.out.println(fileSystem);
    }

    /**
     * 第二种方式
     */
    @Test
    public void getFileSystem2() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());
        System.out.println(fileSystem);
    }

    /**
     * 第三种方式，和第一种类似，第3步不一样
     */
    @Test
    public void getFileSystem3() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.56.120:8020");
        FileSystem fileSystem = FileSystem.newInstance(configuration);
        System.out.println(fileSystem);
    }

    /**
     * 第四种，和第二种类似
     */
    @Test
    public void getFileSystem4() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://192.168.56.120:8020"), new Configuration());
        System.out.println(fileSystem);
    }
}
