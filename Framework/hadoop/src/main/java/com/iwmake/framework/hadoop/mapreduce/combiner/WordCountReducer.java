package com.iwmake.framework.hadoop.mapreduce.combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 四个泛型解释
 * KEYIN:   k2的类型
 * VALUEIN: v2的类型
 * <p>
 * KEYOUT:  k3的类型
 * VALUEOUT:v3的类型
 * @author Dylan
 * @since 2020-11-14
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    /**
     * 将新的k2，v2转成 k3，v3，将k3，v3写入上下文中
     * 新  k2       v2
     * hello   <1,1,1>
     * world   <1,1>
     * hadoop  <1>
     * ---------------------
     * k3       v3
     * hello     3
     * world     2
     * hadoop    1
     * @param key     新k2
     * @param values  集合 新v2
     * @param context 上下文对象
     * @throws IOException
     * @throws InterruptedException
     */
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
