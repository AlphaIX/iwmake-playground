package com.iwmake.framework.hadoop.mapreduce.flowcount.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    /**
     * k1,v1 --> k2,v2
     * k1            v1
     * 0             15666668888,13,25,1200,3456
     * 43            15666668889,11,26,1211,3466
     * ------------------------------------------
     * k2            v2
     * 15666668888   FlowBean(13,25,1200,3456)
     * 15666668889   FlowBean(11,26,1211,3466)
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1、拆分文本行数据，得到手机号 k2
        String[] split = value.toString().split(",");
        String phone = split[0];

        // 2、从行文本数据中提取FlowBean需要的字段，生成FlowBean
        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(Integer.parseInt(split[1]));
        flowBean.setDownFlow(Integer.parseInt(split[2]));
        flowBean.setUpCountFlow(Integer.parseInt(split[3]));
        flowBean.setDownCountFlow(Integer.parseInt(split[4]));

        // 3、将k2 v2写入上下文
        context.write(new Text(phone), flowBean);
    }
}
