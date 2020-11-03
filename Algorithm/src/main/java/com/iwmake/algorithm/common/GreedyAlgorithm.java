package com.iwmake.algorithm.common;

import java.util.*;

/**
 * 贪心算法
 * @author Dylan
 * @since 2020-11-03
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台，放入map
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        // 将各个电台放入broadcasts
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadcasts.put("k1", set1);
        broadcasts.put("k2", set2);
        broadcasts.put("k3", set3);
        broadcasts.put("k4", set4);
        broadcasts.put("k5", set5);

        // 存放所以地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建ArrayList存放电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 临时集合，在遍历过程中，
        HashSet<String> tempSet = new HashSet<>();

        // maxKey保存在一次遍历过程中，当前最优k
        String maxKey = null;

        while (allAreas.size() != 0) {
            maxKey = null;// 必须

            // 遍历broadcasts
            for (String key : broadcasts.keySet()) {
                tempSet.clear();//必须

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);

                // 取出交集赋值给tempSet
                tempSet.retainAll(allAreas);
                // 这里体现贪心算法
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    // 如果 tempSet集合大于，maxKey指向集合，更新maxKey
                    maxKey = key;
                }
            }
            //
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的集合 从all集合中清除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是：" + selects.toString());

    }
}
