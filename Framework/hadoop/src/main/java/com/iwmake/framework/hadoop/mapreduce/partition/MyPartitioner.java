package com.iwmake.framework.hadoop.mapreduce.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Dylan
 * @since 2020-11-14
 */
public class MyPartitioner extends Partitioner<Text, NullWritable> {

    /**
     * 作用
     * 1.定义分区规则
     * 2.返回分区编号
     * @param text
     * @param nullWritable
     * @param i
     * @return
     */
    @Override
    public int getPartition(Text text, NullWritable nullWritable, int i) {
        // 拆分行文本数据（k2），获取指定字段值
        String[] split = text.toString().split(",");
        String numStr = split[5];

        // 判断值与15的大小关系,然后返回分区编号
        if (Integer.parseInt(numStr) > 15) {
            return 1;
        }
        return 0;
    }
}
