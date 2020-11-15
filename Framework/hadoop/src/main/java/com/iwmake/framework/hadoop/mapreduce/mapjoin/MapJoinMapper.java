package com.iwmake.framework.hadoop.mapreduce.mapjoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class MapJoinMapper extends Mapper<LongWritable, Text, Text, Text> {

    private HashMap<String, String> map = new HashMap<>();

    // 1从分布式缓存中读取小表数据放入本地缓存(只需做一次)
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //1获取缓存列表
        URI[] cacheFiles = context.getCacheFiles();

        //2获取指定分布式缓存文件的文件系统
        FileSystem fileSystem = FileSystem.get(cacheFiles[0], context.getConfiguration());

        //3获取文件输入流
        FSDataInputStream inputStream = fileSystem.open(new Path(cacheFiles[0]));

        //4读取文件 并存入map集合
        //将字节输入流转成字符缓冲流FSDataInputStream-->BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //读取小文件内容，已行为单位，并将读取数据存入map
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            map.put(split[0], line);
        }

        //5关闭流
        reader.close();
        fileSystem.close();
    }

    // 2对大表的处理业务逻辑，而且实现大表和小表的join操作
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(",");
        String productId = split[2];
        String productLine = map.get(productId);
        String valueLine = productLine + "\t" + value.toString();
        context.write(new Text(productId), new Text(valueLine));
    }
}
