package com.iwmake.framework.hadoop.hdfs.crud;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 使用JAVA API 操作HDFS
 * @author Dylan
 * @since 2020-11-14
 */
public class CRUD {

    /**
     * hdfs文件遍历
     */
    @Test
    public void listFiles() throws URISyntaxException, IOException {
        // 1、获取FileSystem实例
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());

        // 2、调用listFiles方法
        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path("/"), true);

        // 3. 遍历
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            // 获取文件的绝对路径: hdfs://192.168.56.120:8020/xxx
            System.out.println(fileStatus.getPath() + "---" + fileStatus.getPath().getName());
            // 获取文件的block信息
            BlockLocation[] blocks = fileStatus.getBlockLocations();
            System.out.println("Block数：" + blocks.length);

        }
    }

    /**
     * hdfs创建文件夹
     */
    @Test
    public void mkdirs() throws URISyntaxException, IOException {
        // 1.获取filesystem实例
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());

        // 2.创建文件夹 递归创建
        boolean b1 = fileSystem.mkdirs(new Path("/aaa/bbb/ccc"));
        System.out.println(b1);

        // 3.关闭FileSystem
        fileSystem.close();
    }

    /**
     * hdfs创建文件
     */
    @Test
    public void create() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());

        // 创建文件 目录如果不存在，先递归创建文件夹
        fileSystem.create(new Path("/aaa2/bbb/ccc/a.txt"));

    }

    /**
     * 文件下载 方式1
     */
    @Test
    public void downloadFile() throws URISyntaxException, IOException {
        // 1获取FileSystem
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());

        // 2获取hdfs输入流
        FSDataInputStream inputStream = fileSystem.open(new Path("/a.txt"));

        // 3获取本地输出流
        FileOutputStream outputStream = new FileOutputStream("output/a_1.txt");

        // 4文件拷贝
        IOUtils.copy(inputStream, outputStream);

        // 5关闭流
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        fileSystem.close();
    }

    /**
     * 文件下载 方式2
     */
    @Test
    public void downloadFile2() throws URISyntaxException, IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"),
                new Configuration(),
                "root" // 伪装成root用户，以取得相应文件操作权限
                );

        fileSystem.copyToLocalFile(new Path("/a.txt"), new Path("output/a_5.txt"));

        fileSystem.close();
    }

    /**
     * 文件上传
     */
    @Test
    public void uploadFile() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration());

        fileSystem.copyFromLocalFile(new Path("hadoop.iml"), new Path("/"));

        fileSystem.close();
    }

    /**
     * 小文件合并 再存入hdfs
     */
    @Test
    public void mergeFile() throws URISyntaxException, IOException, InterruptedException {
        // 1 获取FileSystem（分布式文件系统）
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.56.120:8020"), new Configuration(),"root");

        // 2 获取hdfs大文件的输出流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/big_txt.txt"));

        // 3 获取一个本地FileSystem
        LocalFileSystem localFileSystem = FileSystem.getLocal(new Configuration());

        // 4 获取本地文件夹下所有文件详情数组
        FileStatus[] fileStatus = localFileSystem.listStatus(new Path("input"));

        // 5 遍历每个文件，获取每个文件的输入流
        for(FileStatus fStatus: fileStatus){
            FSDataInputStream inputStream = localFileSystem.open(fStatus.getPath());
            // 6 将小文件数据复制到大文件
            IOUtils.copy(inputStream, fsDataOutputStream);
            IOUtils.closeQuietly(inputStream);
        }

        // 7 关闭流
        IOUtils.closeQuietly(fsDataOutputStream);
        localFileSystem.close();
        fileSystem.close();
    }
}
