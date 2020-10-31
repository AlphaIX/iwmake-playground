package com.iwmake.datastructure.tree;

/**
 * 顺序存储二叉树
 * 以数组方式存储二叉树并完成，前序中序后序遍历
 * @author Dylan
 * @since 2020-10-31
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        ArrayBinaryTree tree = new ArrayBinaryTree(arr);

        // 测试前序遍历 1->2->4->5->3->6->7
        //tree.preOrder();
        // 测试中序遍历4->2->5->1->6->3->7
        tree.infixOrder();

    }

    static class ArrayBinaryTree{
        private int[] arr;//存储数据节点的数组

        public ArrayBinaryTree(int[] arr){
            this.arr = arr;
        }

        public void preOrder(){
            preOrder(0);
        }

        // 前序遍历
        /**
         *
         * @param index 数组的下标
         */
        public void preOrder(int index){
            // 判空
            if(arr == null || arr.length==0){
                System.out.println("数组为空，不能按照二叉树前序遍历");
            }
            // 输出当前元素
            System.out.println(arr[index]);

            // 向左递归前序遍历
            if(index*2+1 < arr.length){
                preOrder(index*2+1);
            }
            // 向右递归
            if(index*2+2 < arr.length){
                preOrder(index*2+2);
            }
        }

        public void infixOrder(){
            infixOrder(0);
        }
        public void infixOrder(int index){
            // 判空
            if(arr == null || arr.length==0){
                System.out.println("数组为空，不能按照二叉树前序遍历");
            }
            // 向左
            if(index*2+1 < arr.length){
                infixOrder(index*2+1);
            }
            // 当前
            System.out.println(arr[index]);
            // 向右
            if(index*2+2<arr.length){
                infixOrder(index*2+2);
            }
        }
    }

}
