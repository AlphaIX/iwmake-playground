package com.iwmake.datastructure.tree.avl;


/**
 * 平衡二叉树【AVL树】
 * 添加新节点==>左旋，右旋，双旋
 * @author Dylan
 * @since 2020-11-02
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8}; // 测试左旋
        //int[] arr = {10, 12, 8, 9, 7, 6}; // 测试右旋
        int[] arr = {10, 11,7,6,8,9}; //
        // 创建AVLTree
        AVLTree tree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        // 中序遍历
        tree.infixOrder();

        //System.out.println("在没有平衡处理前：");
        System.out.println("树的高度=" + tree.root.height());
        System.out.println("左子树的高度=" + tree.root.leftHeight());
        System.out.println("右子树的高度=" + tree.root.rightHeight());
        System.out.println("root=" + tree.root);
        System.out.println("root.left=" + tree.root.left);
        System.out.println("root.right=" + tree.root.right);

    }


    /**
     * 二叉平衡树
     */
    static class AVLTree {
        private Node root;

        public Node search(int value) {
            if (root == null) {
                return null;
            } else {
                return root.search(value);
            }
        }

        public Node searchParent(int value) {
            if (root == null) {
                return null;
            } else {
                return root.searchParent(value);
            }
        }

        /**
         * @param node 传入的节点
         * @return 返回以node为根节点 的二叉排序树的最小节点值
         */
        public int delRightTreeMin(Node node) {
            Node target = node;
            // 循环查找左节点
            while (target.left != null) {
                target = target.left;
            }
            // 删除最小节点
            delNode(target.value);
            return target.value;
        }

        public void delNode(int value) {
            if (root == null) {
                return;
            } else {
                Node targetNode = search(value);
                if (targetNode == null) {
                    return;
                }
                // targetNode是root
                if (root.left == null && root.right == null) {
                    root = null;
                    return;
                }
                // 找到targetNode的父节点
                Node parent = searchParent(value);
                // a.叶子节点
                if (targetNode.left == null && targetNode.right == null) {
                    // 判断targetNode是父节点的左子节点还是右子节点
                    if (parent.left != null && parent.left.value == value) {
                        parent.left = null;
                    } else if (parent.right != null && parent.right.value == value) {
                        parent.right = null;
                    }
                }
                // b.有两颗子树的节点
                else if (targetNode.left != null && targetNode.right != null) {
                    int minVal = delRightTreeMin(targetNode.right);
                    targetNode.value = minVal;
                }
                // c.一颗子树的节点
                else {
                    // 左子节点
                    if (targetNode.left != null) {
                        if (parent != null) {
                            if (parent.left.value == value) {
                                parent.left = targetNode.left;
                            } else {
                                parent.right = targetNode.left;
                            }
                        } else {
                            root = targetNode.left;
                        }

                    } else {
                        if (parent != null) {
                            if (parent.left.value == value) {
                                parent.left = targetNode.right;
                            } else {
                                parent.right = targetNode.right;
                            }
                        } else {
                            root = targetNode.right;
                        }
                    }

                }
            }
        }

        public void add(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        public void infixOrder() {
            if (root != null) {
                root.infixOrder();
            } else {
                System.out.println("二叉排序树为空");
            }
        }
    }


    /**
     * 节点
     */
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 返回左子树高度
         * @return
         */
        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        /**
         * 返回右子树高度
         * @return
         */
        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }

        /**
         * 返回以当前节点为根节点的树的高度
         * @return
         */
        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height())
                    + 1;
        }

        /**
         * 左旋转
         */
        public void leftRotate() {
            // 创建新的节点,以当前根节点值
            Node newNode = new Node(value);
            // 把新的节点的左子树为，设置为当前节点的左子树
            newNode.left = left;
            // 把新的节点的右子树，设置为当前节点的右子树的左子树
            newNode.right = right.left;
            // 把当前节点值替换成右子节点的值
            value = right.value;
            // 把当前节点的右子树设置当前节点的右子树的右子树
            right = right.right;
            // 把当前节点的左(节点)子树,设置成新的节点
            left = newNode;
        }

        /**
         * 右旋
         */
        public void rightRotate() {
            Node newNode = new Node(value);
            newNode.right = right;
            newNode.left = left.right;
            value = left.value;
            left = left.left;
            right = newNode;
        }


        /**
         * 查找要删除的节点
         * @param value
         * @return
         */
        public Node search(int value) {
            if (value == this.value) {
                return this;
            } else if (value < this.value) {// 向左子树递归查找
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }

        /**
         * 查找要删除节点的父节点
         * @param value
         * @return
         */
        public Node searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (value < this.value && this.left != null) {
                    return this.left.searchParent(value);// 左子树递归查找
                } else if (value >= this.value && this.right != null) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }
        }

        /**
         * 添加节点
         * @param node
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }

            if (node.value < this.value) {//左子树
                // 当前节点，左子节点为空
                if (this.left == null) {
                    this.left = node;
                } else {
                    // 递归向左子树添加
                    this.left.add(node);
                }
            } else {// 右子树
                if (this.right == null) {
                    this.right = node;
                } else {
                    // 递归向右子树添加
                    this.right.add(node);
                }
            }

            // 当添加完一个节点后，需要判断是否需要左旋,（右子树高度-左子树高度） > 1
            if (rightHeight() - leftHeight() > 1) {
                // 如果它的右子树的左子树的高度大于它的右子树的右子树高度，先针对它的右子树右旋
                if(right!=null && right.leftHeight() > right.rightHeight()){
                    right.rightRotate();
                    // 针对当前节点左旋
                    leftRotate();
                }else {
                    // 直接左旋
                    leftRotate();
                }
                return;// 必须要！！！
            }
            // 当添加完一个节点后，如果 （左子树高度-右子树高度） > 1  需要右旋
            if (leftHeight() - rightHeight() > 1) {
                // 如果它的左子树的右子树高度大于它的左子树的左子树高度 先针对它的左子树左旋
                if(left !=null && left.rightHeight() > left.leftHeight()){
                    left.leftRotate();
                    // 针对当前节点右旋
                    rightRotate();
                }else {
                    // 直接右旋即可
                    rightRotate();
                }
            }

        }

        // 中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }

            System.out.println(this);

            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
