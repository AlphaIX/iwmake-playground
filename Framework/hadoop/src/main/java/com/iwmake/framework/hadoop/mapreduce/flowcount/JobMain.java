package com.iwmake.framework.hadoop.mapreduce.flowcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 需求1：流量求和
 * @author Dylan
 * @since 2020-11-14
 */
public class JobMain extends Configured implements Tool {

    /**
     * 该方法用于指定一个job任务
     * @param strings
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] strings) throws Exception {
        // 1.创建一个job任务对象
        Job job = Job.getInstance(super.getConf(), "mapreduce_flowcount");

        // 2.配置job任务对象（八个步骤）
        // 一、指定文件的读取方式和读取路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("file:///Users/ydy/Desktop/input/flowcount"));

        // 二、指定map阶段的处理方式，和数据类型
        job.setMapperClass(FlowCountMapper.class);
        // 设置map阶段k2的类型,v2的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 三(分区)、四(排序)、五，规约、六(分组) Shuffle阶段采用默认方式

        // 七、指定reduce阶段的处理方式，和数据类型
        job.setReducerClass(FlowCountReducer.class);
        // 设置reduce阶段k3的类型，v3的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 八、设置输出类型
        job.setOutputFormatClass(TextOutputFormat.class);
        // 设置输出路径
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/ydy/Desktop/out/flowcount/f_out"));

        // 等待任务结束
        boolean b = job.waitForCompletion(true);

        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        // 启动job任务
        int stat = ToolRunner.run(configuration, new JobMain(), args);
        System.exit(stat);
    }
}
