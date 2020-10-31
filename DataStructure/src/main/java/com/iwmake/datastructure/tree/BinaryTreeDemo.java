package com.iwmake.datastructure.tree;

/**
 * 二叉树demo
 * @author Dylan
 * @since 2020-10-31
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建一个二叉树
        BinaryTree tree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 先手动创建二叉树，后面学习递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);

        node3.setLeft(node5);

        tree.setRoot(root);

        /**
         *            1
         *           / \
         *          2   3
         *             / \
         *            5  4
         *
         */

        // 测试遍历
//        // 前序 1->2->3->5->4
//        System.out.println("前序：");
//        tree.preOrder();
//
//        // 中序 2->1->5->3->4
//        System.out.println("中序：");
//        tree.infixOrder();
//
//        // 后序 2->5->4->3->1
//        System.out.println("后序：");
//        tree.postOrder();

        // 测试查找
        // 前序 4次
//        System.out.println("前序查找：");
//        HeroNode resNode = tree.preOrderSearch(5);
//        if(resNode!=null){
//            System.out.println("找到信息："+resNode.toString());
//        }else {
//            System.out.println("没找到信息！！！");
//        }

        // 中序 3次
//        System.out.println("中序查找：");
//        HeroNode resNode = tree.infixOrderSearch(5);
//        if(resNode!=null){
//            System.out.println("找到信息："+resNode.toString());
//        }else {
//            System.out.println("没找到信息！！！");
//        }

        // 后序 2次
//        System.out.println("后序查找：");
//        HeroNode resNode = tree.postOrderSearch(5);
//        if(resNode!=null){
//            System.out.println("找到信息："+resNode.toString());
//        }else {
//            System.out.println("没找到信息！！！");
//        }

        // 测试删除节点
        System.out.println("删除前：");
        tree.preOrder();
        tree.delNode(5);
        System.out.println("删除后");
        tree.preOrder();


    }

    /**
     * 定义二叉树
     */
    static class BinaryTree {
        private HeroNode root;

        public void setRoot(HeroNode root) {
            this.root = root;
        }

        // 前序遍历
        public void preOrder() {
            if (this.root != null) {
                this.root.preOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }

        // 中序遍历
        public void infixOrder() {
            if (this.root != null) {
                this.root.infixOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }

        // 后序遍历
        public void postOrder() {
            if (this.root != null) {
                this.root.postOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }

        // 前序查找
        public HeroNode preOrderSearch(int no) {
            if (root != null) {
                return root.preOrderSearch(no);
            }
            return null;
        }

        public HeroNode infixOrderSearch(int no) {
            if (root != null) {
                return root.infixOrderSearch(no);
            }
            return null;
        }

        public HeroNode postOrderSearch(int no) {
            if (root != null) {
                return root.postOrderSearch(no);
            }
            return null;
        }

        // 删除节点
        public void delNode(int no) {
            if (root != null) {
                // 如果只有一个root节点，必须先判断
                if (root.getNo() == no) {
                    root = null;
                } else {
                    root.delNode(no);
                }

            } else {
                System.out.println("树为空！");
            }
        }

    }

    /**
     * 创建节点
     */
    static class HeroNode {
        private int no;
        private String name;
        private HeroNode left;// 默认null
        private HeroNode right;// 默认null

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode getLeft() {
            return left;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public HeroNode getRight() {
            return right;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }

        // 前序遍历
        public void preOrder() {
            System.out.println(this); // 先输出父节点
            //递归向左子树前序遍历
            if (this.left != null) {
                this.left.preOrder();
            }
            // 递归向右子树前序遍历
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        // 中序遍历
        public void infixOrder() {
            // 递归向左子中序树遍历
            if (this.left != null) {
                this.left.infixOrder();
            }
            // 输出父节点
            System.out.println(this);
            // 递归向右子树中序遍历
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        // 后序遍历
        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }

        // 前序查找
        public HeroNode preOrderSearch(int no) {
            System.out.println("进入pre查找！");
            // 比较当前节点是不是
            if (this.getNo() == no) {
                return this;
            }
            HeroNode resNode = null;
            if (this.left != null) {
                resNode = this.left.preOrderSearch(no);
            }
            if (resNode != null) { // 左子树找到
                return resNode;
            }

            if (this.right != null) {
                resNode = this.right.preOrderSearch(no);
            }
            return resNode;
        }

        // 中序查找
        public HeroNode infixOrderSearch(int no) {

            HeroNode resNode = null;
            if (this.left != null) {
                resNode = this.left.infixOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }

            System.out.println("进入infix查找！");
            if (this.getNo() == no) {
                return this;
            }

            if (this.right != null) {
                resNode = this.right.infixOrderSearch(no);
            }
            return resNode;
        }

        // 后序查找
        public HeroNode postOrderSearch(int no) {
            HeroNode resNode = null;
            if (this.left != null) {
                resNode = this.left.postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }

            if (this.right != null) {
                resNode = this.right.postOrderSearch(no);
            }
            if (resNode != null) {
                return resNode;
            }

            System.out.println("进入post查找！");
            if (this.getNo() == no) {
                return this;
            }
            return resNode;
        }

        // 删除节点
        public void delNode(int no) {
            if (this.left != null && this.left.getNo() == no) {
                this.left = null;
                return;
            }
            if (this.right != null && this.right.getNo() == no) {
                this.right = null;
                return;
            }
            if (this.left != null) {
                this.left.delNode(no);
            }
            if (this.right != null) {
                this.right.delNode(no);
            }
        }
    }
}
