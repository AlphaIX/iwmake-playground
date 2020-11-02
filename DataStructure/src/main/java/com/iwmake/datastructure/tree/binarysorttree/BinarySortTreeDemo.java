package com.iwmake.datastructure.tree.binarysorttree;

/**
 * 二叉排序树
 * @author Dylan
 * @since 2020-11-02
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        // 循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }

        /**
         *               7
         *             /   \
         *            3    10
         *           / \   / \
         *          1   5 9  12
         *           \
         *            2
         */
        // 中序遍历二叉排序树 1->2->3->5->7->9->10->12
        tree.infixOrder();

        // 测试删除节点
        System.out.println("测试删除节点");
        tree.delNode(10);
        tree.infixOrder();
    }


    static class BinarySortTree {
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
