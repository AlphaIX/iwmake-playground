package com.iwmake.framework.hadoop.mapreduce.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
        // 1.创建一个job任务对象
        Job job = Job.getInstance(super.getConf(), "partition_mapreduce");
        // 2.配置job任务（8个步骤）
        // 一、设置输入类型和输入路径
        job.setInputFormatClass(TextInputFormat.class);
        //TextInputFormat.addInputPath(job, new Path("hdfs://node01:8020/input"));
        TextInputFormat.addInputPath(job, new Path("file:///Users/ydy/Desktop/input/partition"));

        // 二、设置Mapper类和数据类型(k2,v2)
        job.setMapperClass(PartitionMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 三、四、五、六 Shuffle阶段
        // 三、分区 ==> 指定分区类
        job.setPartitionerClass(MyPartitioner.class);
        // 四五六使用默认方式

        // 七、设置Reducer类和数据类型(k3,v3)
        job.setReducerClass(PartitionReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // 设置reduce task的个数 根据分区大小
        job.setNumReduceTasks(2);

        // 八、指定输出类 和 输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        //TextOutputFormat.setOutputPath(job, new Path("hdfs://node01:8020/out/partition_out"));
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/ydy/Desktop/out/partition/p_out"));

        // 3.等待任务结束
        boolean b = job.waitForCompletion(true);

        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int status = ToolRunner.run(new Configuration(), new JobMain(), args);
        System.exit(status);
    }
}
