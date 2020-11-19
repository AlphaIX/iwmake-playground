package com.iwmake.framework.hadoop.mapreduce.reducejoin;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class ReduceJoinReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String first = "";
        String second = "";
        for(Text value: values){
            if(value.toString().startsWith("p")){
                first = value.toString();
            }else {
                second = value.toString();
            }
        }
        String v3 = first +"\t"+ second;

        context.write(key, new Text(v3));
    }
}
