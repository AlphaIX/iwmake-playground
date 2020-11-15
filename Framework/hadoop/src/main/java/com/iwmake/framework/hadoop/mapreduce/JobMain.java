package com.iwmake.framework.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
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

    /**
     * 该方法用于指定一个job任务
     * @param strings
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] strings) throws Exception {
        // 1.创建一个job任务对象
        Job job = Job.getInstance(super.getConf(), "wordcount");
        // TODO 如果集群环境下打包的jar不能运行则需要加该配置
        //job.setJarByClass(JobMain.class);

        // 2.配置job任务对象（八个步骤）
        // 一、指定文件的读取方式和读取路径
        job.setInputFormatClass(TextInputFormat.class);
        // 指定输入路径为hdfs文件系统 生成集群环境使用
        //TextInputFormat.addInputPath(job, new Path("hdfs://192.168.56.120:8020/wordcount"));
        // 指定输入路径为本地文件系统，单机环境，开发测试时使用,win系统file:///E:\\abc\\input\\aaa
        TextInputFormat.addInputPath(job, new Path("file:///Users/ydy/Desktop/input"));

        // 二、指定map阶段的处理方式，和数据类型
        job.setMapperClass(WordCountMapper.class);
        // 设置map阶段k2的类型,v2的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 三、四、五、六 Shuffle阶段采用默认方式

        // 七、指定reduce阶段的处理方式，和数据类型
        job.setReducerClass(WordCountReducer.class);
        // 设置reduce阶段k3的类型，v3的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 八、设置输出类型
        job.setOutputFormatClass(TextOutputFormat.class);
        // 设置输出路径
        // TODO 不管是集群环境还是本地环境，输出目录不能已经存在，
        //  程序会自动创建目录 ==> 可以通过FileSystem 判断目标目录是否已经存在，如果存在先删除目标目录
        // 指定输出路径为hdfs 生产集群环境使用
        //TextOutputFormat.setOutputPath(job, new Path("hdfs://192.168.56.120:8020/wordcount_out"));
        // 指定输出路径为本地 ，单机测试时
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/ydy/Desktop/output"));

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
