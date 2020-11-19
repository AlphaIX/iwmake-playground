package com.iwmake.framework.hadoop.mapreduce.flowcount.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    /**
     * 将 Shuffle之后新的k2，v2 --> k3, v3
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        // 遍历v2集合，将对应字段累加
        int upFlow = 0;
        int downFlow = 0;
        int upCountFlow = 0;
        int downCountFlow = 0;
        for (FlowBean value : values) {
            upFlow += value.getUpFlow();
            upCountFlow += value.getUpCountFlow();
            downFlow += value.getDownFlow();
            downCountFlow += value.getDownCountFlow();
        }

        // 生成v3对象
        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(upFlow);
        flowBean.setUpCountFlow(upCountFlow);
        flowBean.setDownFlow(downFlow);
        flowBean.setDownCountFlow(downCountFlow);

        // 将k3 v3写入上下文
        context.write(key, flowBean);
    }
}
