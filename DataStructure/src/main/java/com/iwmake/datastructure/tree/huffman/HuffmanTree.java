package com.iwmake.datastructure.tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * @author Dylan
 * @since 2020-10-31
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    // 前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树无法遍历");
        }
    }

    /**
     * 创建赫夫曼树
     * @param arr
     * @return 根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }

        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);
            System.out.println(nodes.toString());

            // 构建根节点权值最小的二叉树
            // 1去出最小权值节点
            Node leftNode = nodes.get(0);
            // 2取出次小权值节点
            Node rightNode = nodes.get(1);
            // 3构建parent，其权值=left+right权值
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 4从list中删除处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5将parent加入nodes
            nodes.add(parent);

//        System.out.println("第一次："+nodes.toString());
        }
        // 返回赫夫曼树的 root节点
        return nodes.get(0);
    }

    /**
     * 节点类，实现Comparable接口，方便比较
     */
    static class Node implements Comparable<Node> {
        public int value; // 节点权值
        public Node left; // 指向左子节点
        public Node right;// 指向右子节点

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            // 从小到大排序
            return this.value - o.value;
        }

        //前序遍历
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
