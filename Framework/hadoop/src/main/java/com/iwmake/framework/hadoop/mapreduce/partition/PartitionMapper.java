package com.iwmake.framework.hadoop.mapreduce.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * k1 行偏移量 LongWritable
 * v1 行文本数据 Text
 * k2 行文本数据 Text
 * v2 NullWritable
 * @author Dylan
 * @since 2020-11-14
 */
public class PartitionMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    /**
     * map方法将k1，v1 转换成k2，v2
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 方式1：定义计数器
        Counter counter = context.getCounter("MR_COUNTER", "partition_counter");
        counter.increment(1L);
        context.write(value, NullWritable.get());
    }
}
