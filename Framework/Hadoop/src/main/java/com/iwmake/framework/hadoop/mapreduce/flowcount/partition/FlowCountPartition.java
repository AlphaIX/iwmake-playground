package com.iwmake.framework.hadoop.mapreduce.flowcount.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 泛型 k2，v2
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowCountPartition extends Partitioner<Text, FlowBean> {
    /**
     * 指定分区规则
     * 135开头手机号 一个区
     * 152开头手机号 一个区
     * 其他开头手机号 一个区
     * @param text     k2
     * @param flowBean v2
     * @param i        reduce task个数
     * @return
     */
    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        // 获取手机号
        String phone = text.toString();
        // 判断手机号以什么开头,返回对应的分区编号
        if (phone.startsWith("135")) {
            return 0;
        }
        if (phone.startsWith("152")) {
            return 1;
        }
        return 2;
    }
}
