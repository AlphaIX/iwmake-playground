package com.iwmake.framework.hadoop.mapreduce.flowcount.partition;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * MapReduce Bean必须实现hadoop序列化接口
 * @author Dylan
 * @since 2020-11-15
 */
public class FlowBean implements Writable {
    private Integer upFlow;//上行数据包数
    private Integer downFlow;//下行数据包数
    private Integer upCountFlow;//上行流量总和
    private Integer downCountFlow;//下行流量总和

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(Integer upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public Integer getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(Integer downCountFlow) {
        this.downCountFlow = downCountFlow;
    }

    @Override
    public String toString() {
        return upFlow +
                "\t" + downFlow +
                "\t" + upCountFlow +
                "\t" + downCountFlow;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(upCountFlow);
        out.writeInt(downCountFlow);
    }

    /**
     * 反序列化
     * @param input
     * @throws IOException
     */
    @Override
    public void readFields(DataInput input) throws IOException {
        this.upFlow = input.readInt();
        this.downFlow = input.readInt();
        this.upCountFlow = input.readInt();
        this.downCountFlow = input.readInt();
    }
}
