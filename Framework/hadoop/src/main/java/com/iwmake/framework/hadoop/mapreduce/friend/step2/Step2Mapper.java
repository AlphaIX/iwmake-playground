package com.iwmake.framework.hadoop.mapreduce.friend.step2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class Step2Mapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String v2 = split[1];

        String[] userArr = split[0].split("-");
        Arrays.sort(userArr);
        for (int i = 0; i < userArr.length - 1; i++) {
            for (int j = i + 1; j < userArr.length; j++) {
                String k2 = userArr[i] + "-" + userArr[j];
                context.write(new Text(k2), new Text(v2));
            }
        }
    }
}
