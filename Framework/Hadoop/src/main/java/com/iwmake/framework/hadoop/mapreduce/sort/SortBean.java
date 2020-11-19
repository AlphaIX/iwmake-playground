package com.iwmake.framework.hadoop.mapreduce.sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * MapReduce中序列化和反序列化，以及排序
 * @author Dylan
 * @since 2020-11-14
 */
public class SortBean implements WritableComparable<SortBean> {
    private String word;
    private int num;

    /**
     * 实现比较器，指定排序规则
     * 先按第一列按照字典顺序排序， // abc aac ==> aac abc
     * 如果第一列相同，按第二列数字升序排列
     * @param o
     * @return
     */
    @Override
    public int compareTo(SortBean o) {
        // 先对第一列排序，Word排序
        int res = this.word.compareTo(o.word);
        // 如果第一列相同，按照第二列升序排序
        if (res == 0) {
            return this.num - o.num;
        }
        return res;
    }

    /**
     * 实现序列化
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(word);
        dataOutput.writeInt(num);
    }

    /**
     * 实现反序列化
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.word = dataInput.readUTF();
        this.num = dataInput.readInt();
    }

    public SortBean() {
    }

    public SortBean(String word, int num) {
        this.word = word;
        this.num = num;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return word + "\t" + num;
    }
}
