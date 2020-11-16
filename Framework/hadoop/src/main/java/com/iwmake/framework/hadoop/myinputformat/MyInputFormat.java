package com.iwmake.framework.hadoop.myinputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class MyInputFormat extends FileInputFormat<NullWritable, BytesWritable> {
    @Override
    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        // 1.创建自定义RecordReader对象
        MyRecordReader myRecordReader = new MyRecordReader();
        // 2.将inputSplit和context传给myRecordReader
        myRecordReader.initialize(inputSplit, taskAttemptContext);

        return myRecordReader;
    }

    //设置文件是否可以被切割，因为我们要合并小文件不允许切割
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false;
    }
}
