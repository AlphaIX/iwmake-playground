package com.iwmake.framework.hadoop.mapreduce.friend.step1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class Step1Mapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split1 = value.toString().split(":");
        String v2 = split1[0];
        String[] split2 = split1[1].split(",");
        for (String k2 : split2) {
            context.write(new Text(k2), new Text(v2));
        }
    }
}
