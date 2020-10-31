package com.iwmake.datastructure.tree;

/**
 * 线索化二叉树，中序demo
 * @author Dylan
 * @since 2020-10-31
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试中序线索化二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        // 现在收到创建二叉树，后面学习递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNode();

        // 查看10号节点
        HeroNode leftNode = node5.getLeft();// -->3 jack
        HeroNode rightNode = node5.getRight();// --> 1 tom
        System.out.println(leftNode);
        System.out.println(rightNode);

        // 当线索化后不能使用原先的遍历方式了
        //tree.infixOrder();
        // 线索化的方式遍历 8->3->10->1->14->6
        System.out.println("线索化遍历：");
        tree.threadedList();

    }

    /**
     * 实现了线索化功能的二叉树
     */
    static class ThreadedBinaryTree {
        private HeroNode root;

        // 为了实现线索化需要一个指向，当前节点前驱节点的指针
        private HeroNode prev = null;

        public void setRoot(HeroNode root) {
            this.root = root;
        }

        public void threadedNode(){
            this.threadedNode(root);
        }
        // 中序线索化二叉树的方法
        /**
         *
         * @param node 当前需要线索化的节点
         */
        public void threadedNode(HeroNode node){
            if(node == null){
                return;
            }
            // 1.先线索化左子树
            threadedNode(node.getLeft());
            // 2.线索化当前节点
            // 处理当前节点的前驱节点
            if(node.getLeft() == null){
                node.setLeft(prev);
                node.setLeftType(1);
            }
            // 处理后继节点
            if(prev!=null && prev.getRight()==null){
                // 让前驱节点的右指针指向当前节点
                prev.setRight(node);
                prev.setRightTye(1);
            }
            //每处理一个节点后，让当前节点是下一个节点的前驱节点
            prev = node;

            // 3.线索化右子树
            threadedNode(node.getRight());
        }

        // 遍历线索化二叉树,不能在使用之前的前序，中序，后序遍历
        public void threadedList(){
            HeroNode node = root;
            while (node!=null){
                // 循环找到leftType == 1的节点，第一个找到就是8节点
                // 后面随着遍历二变化，因为当leftType==1说明节点是按线索化处理后的有效节点
                while (node.getLeftType()==0){
                    node = node.getLeft();
                }
                // 打印当前节点
                System.out.println(node);
                // 如果当前节点的右指针指向的是后继节点，就一直输出
                while (node.getRightTye()==1){
                    // 获取当前节点的后继节点
                    node = node.getRight();
                    System.out.println(node);
                }
                // 替换遍历的节点
                node = node.getRight();
            }
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

        //说明
        // 如果leftType==0表示指向左子树，如果==1表示指向前驱节点
        // 如果rightType==0表示指向右子树，如果==1表示指向后继节点
        private int leftType;
        private int rightTye;

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightTye() {
            return rightTye;
        }

        public void setRightTye(int rightTye) {
            this.rightTye = rightTye;
        }

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
