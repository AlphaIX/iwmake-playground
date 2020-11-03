package com.iwmake.algorithm.common;

import java.util.Arrays;

/**
 * KMP算法
 * @author Dylan
 * @since 2020-11-03
 */
public class KMPMatch {
    public static void main(String[] args) {
        // KMP匹配
        String str1 = "AAB CABDCA CABDCABDCADE";
        //String str2 = "AAB";
        String str2 = "CABDCAD";
        int[] next = kmpNext("CABDCAD");
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpMatch(str1, str2, next);
        System.out.println("index=" + index);
    }

    /**
     * kmp匹配算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return
     */
    public static int kmpMatch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 处理不相等的情况，kmp核心,调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) { //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取一个字符串(子串)部分匹配值
    public static int[] kmpNext(String dest) {
        // 创建一个数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;// 如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j),我们需要从next[j-1]获取新的j
            // 直到dest.charAt(i) == dest.charAt(j)成立退出
            // kmp算法核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) { //条件值满足，部分匹配值加1
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}

/**
 * 暴力匹配算法
 */
class ViolenceMatch {
    public static void main(String[] args) {
        // 测试暴力匹配
        String str1 = "AAB CABDCA CABDCABDCADE";
        String str2 = "CABDCAD";

        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);

    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i索引指向s1
        int j = 0; // j索引指向s2
        while (i < s1Len && j < s2Len) { // 保证匹配时不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else { // 没有匹配成功
                // 令i=i-(j-1),j=0
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }


}