package com.iwmake.framework.hadoop.mapreduce.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * 没有reduce的job任务
 * @author Dylan
 * @since 2020-11-15
 */
public class JobMain extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        // 1获取job对象
        Job job = Job.getInstance(super.getConf(), "map_join_job");

        // 2设置job对象(将小表放在分布式缓存中)
        //DistributedCache.addCacheFile(new URI("hdfs://node01:8020/cache/products.txt"), super.getConf());
        job.addCacheFile(new URI("hdfs://node01:8020/cache/products.txt"));

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("file:///Users/ydy/Desktop/input/join_map"));
        job.setMapperClass(MapJoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/ydy/Desktop/out/map_join/f_out"));

        // 3等待任务结束
        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        //启动job任务
        int run = ToolRunner.run(new Configuration(), new JobMain(), args);

        System.exit(run);
    }
}
