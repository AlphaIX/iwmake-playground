package com.iwmake.framework.hadoop.mapreduce.sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-14
 */
public class SortReducer extends Reducer<SortBean, NullWritable, SortBean, NullWritable> {

    /**
     * 将经过Shuffle后新的 k2，v2 转为k3，v3
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(SortBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
