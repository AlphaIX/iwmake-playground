package com.iwmake.framework.hadoop.mapreduce.friend.step1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class Step1Reducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer buffer = new StringBuffer();
        for (Text text : values) {
            buffer.append(text.toString()).append("-");
        }

        context.write(new Text(buffer.toString()), key);
    }
}
