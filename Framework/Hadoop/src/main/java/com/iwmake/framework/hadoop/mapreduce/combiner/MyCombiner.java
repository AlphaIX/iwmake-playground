package com.iwmake.framework.hadoop.mapreduce.combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 定义规约 和Reduce类似
 * 定义规约的目的是 提前将当前map task的任务结果进行reduce，
 * 以减少到最终Reduce的网络IO次数
 * @author Dylan
 * @since 2020-11-15
 */
public class MyCombiner extends Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        // 1.遍历集合将集合中的数字相加，得到v3
        for (LongWritable value : values) {
            count += value.get();
        }
        // 2.将k3和v3写入上下文中
        context.write(key, new LongWritable(count));
    }
}
