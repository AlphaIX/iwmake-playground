package com.iwmake.framework.hadoop.mapreduce.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.判断数据来自哪个文件
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String fileName = fileSplit.getPath().getName();
        if (fileName.equals("products.txt")) {
            // 来自商品表
            String[] split = value.toString().split(",");
            String productId = split[0];
            // 将k1，v1 转为k2，v2 写入上下文
            context.write(new Text(productId), value);
        } else {
            // 来自订单表
            String[] split = value.toString().split(",");
            String productId = split[2];
            // 将k1，v1 转为k2，v2 写入上下文
            context.write(new Text(productId), value);
        }

    }
}
