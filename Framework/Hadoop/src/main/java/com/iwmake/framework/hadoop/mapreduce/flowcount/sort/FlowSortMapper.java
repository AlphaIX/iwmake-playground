package com.iwmake.framework.hadoop.mapreduce.flowcount.sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowSortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    /**
     *
     * k1,v1 --> k2,v2
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 拆分行文本数据v1，封装FlowBean k2
        String[] split = value.toString().split("\t");
        FlowBean flowBean = new FlowBean(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));

        // v2
        String phone = split[0];

        // 存入上下文
        context.write(flowBean, new Text(phone));

    }
}
