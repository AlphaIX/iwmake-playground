package com.iwmake.framework.hadoop.mapreduce.sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-14
 */
public class SortMapper extends Mapper<LongWritable, Text, SortBean, NullWritable> {

    /**
     * k1,v1 --> k2,v2
     * k1    v1
     * 0     a,3
     * 5     b,7
     * ------------
     * k2             v2
     * SortBean(a  3)    NullWritable
     * SortBean(b  7)    NullWritable
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1、将行文本v1，拆分，封装到SortBean对象得到k2
        String[] split = value.toString().split(",");
        SortBean bean = new SortBean(split[0], Integer.parseInt(split[1]));

        // 2、将k2，v2写入上下文
        context.write(bean, NullWritable.get());
    }
}
