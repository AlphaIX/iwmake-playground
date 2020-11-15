package com.iwmake.framework.hadoop.mapreduce.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author Dylan
 * @since 2020-11-14
 */
public class JobMain extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        // 1.创建job任务对象
        Job job = Job.getInstance(super.getConf(), "mapreduce_sort");

        // 2.配置job任务对象（8个步骤）
        // 一 设置输入类，和路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("file:///Users/ydy/Desktop/input/sort"));

        // 二、设置Mapper类和数据类型k2,v2
        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(SortBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 三四五六 Shuffle 使用默认
        // 三分区
        // 四排序 SortBean已经实现排序接口
        // 五规约
        // 分组

        // 七、设置Reducer类和数据类型，k3 v3
        job.setReducerClass(SortReducer.class);
        job.setOutputKeyClass(SortBean.class);
        job.setOutputValueClass(NullWritable.class);

        // 八、设置输出类和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/ydy/Desktop/out/sort/s_out"));

        // 3.等待job任务完成
        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int status = ToolRunner.run(new Configuration(), new JobMain(), args);
        System.exit(status);
    }
}
