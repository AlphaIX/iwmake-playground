package com.iwmake.framework.hadoop.mapreduce.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * k2 Text
 * v2 NullWritable
 * k3 Text
 * v3 NullWritable
 * @author Dylan
 * @since 2020-11-14
 */
public class PartitionReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
    public static enum Counter{
        MY_INPUT_RECORDS,MY_INPUT_BYTES
    }
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 方式2：定义计数器，使用枚举
        context.getCounter(Counter.MY_INPUT_RECORDS).increment(1L);
        context.write(key, NullWritable.get());
    }
}
