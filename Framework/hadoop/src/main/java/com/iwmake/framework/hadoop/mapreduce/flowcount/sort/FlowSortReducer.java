package com.iwmake.framework.hadoop.mapreduce.flowcount.sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowSortReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        Text k3 = null;
        while (iterator.hasNext()){
            k3 = iterator.next();
        }

        context.write(k3, key);
    }
}
