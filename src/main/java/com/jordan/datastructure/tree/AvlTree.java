package com.jordan.datastructure.tree;

import java.util.Objects;

public class AvlTree<T extends Comparable> {

    private TreeNode<T> root;

    private int getNodeHeight(TreeNode node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private int getBalanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return getNodeHeight(node.getLeft()) - getNodeHeight(node.getRight());

    }

    private void updateNodeHeight(TreeNode node) {
        if (node == null)
            return;
        node.setHeight(Math.max(getNodeHeight(node.getLeft()), getNodeHeight(node.getRight())) + 1);
    }

    private TreeNode<T> rightRotation(TreeNode<T> node) {
        TreeNode<T> leftChild = node.getLeft();
        TreeNode<T> tempNode = leftChild.getRight();

        node.setLeft(tempNode);
        leftChild.setRight(node);

        updateNodeHeight(node);
        updateNodeHeight(leftChild);

        return leftChild;
    }

    private TreeNode<T> leftRotation(TreeNode<T> node) {
        TreeNode<T> rightChild = node.getRight();
        TreeNode<T> tempNode = rightChild.getLeft();

        node.setRight(tempNode);
        rightChild.setLeft(node);

        updateNodeHeight(node);
        updateNodeHeight(rightChild);

        return rightChild;
    }

    private TreeNode<T> updateBalance(TreeNode<T> node) {

        int bf = getBalanceFactor(node);
        if (bf > 1) {
            if (node.getLeft() != null && node.getLeft().getLeft() != null) {
                // LL type
                return rightRotation(node);
            } else {
                // LR type
                node.setLeft(leftRotation(node.getLeft()));
                return rightRotation(node);
            }
        } else if (bf < -1) {
            if (node.getRight() != null && node.getRight().getRight() != null) {
                // RR type
                return leftRotation(node);
            } else {
                // RL type
                node.setRight(rightRotation(node.getRight()));
                return leftRotation(node);
            }
        }
        return node;
    }

    public void insertElement(T data) {
        if (this.root == null)
            this.root = new TreeNode<>(data);
        else
            this.root = this.insertElement(data, this.root);
    }

    private TreeNode<T> insertElement(T data, TreeNode<T> currentNode) {

        if (currentNode == null) {
            return new TreeNode<>(data);
        }

        if (currentNode.getValue().compareTo(data) <= -1)
            currentNode.setRight(this.insertElement(data, currentNode.getRight()));
        else
            currentNode.setLeft(this.insertElement(data, currentNode.getLeft()));

        updateNodeHeight(currentNode);
        currentNode = updateBalance(currentNode);

        return currentNode;
    }

    public TreeNode deleteElement(T data) {
        if (root == null)
            return null;
        root = deleteElement(data, root);
        return root;
    }

    private TreeNode<T> deleteElement(T data, TreeNode<T> node) {
        if (node == null)
            return null;

        if (node.getValue().compareTo(data) < 0) {
            node.setRight(deleteElement(data, node.getRight()));
        } else if (node.getValue().compareTo(data) > 0) {
            node.setLeft(deleteElement(data, node.getLeft()));
        } else {
            // case 1: target data is a leaf node
            if (node.getLeft() == null && node.getRight() == null)
                return null;
                // case 2: target data only has right child
            else if (node.getLeft() == null)
                return node.getRight();
                // case 3: target data only has left child
            else if (node.getRight() == null)
                return node.getLeft();
                // case 4: target data has left and right child
            else {
                // find the node which has the max value on the left subtree
                TreeNode<T> leftMaxValueTreeNode = findMaxValueNode(node.getLeft());

                // swap the value
                T value = node.getValue();
                node.setValue(leftMaxValueTreeNode.getValue());
                leftMaxValueTreeNode.setValue(value);

                // delete the data
                node.setLeft(deleteElement(data, node.getLeft()));
            }
        }
        // update tree height
        updateNodeHeight(node);

        // update tree balance
        return updateBalance(node);

    }

    public boolean contains(T key) {
        TreeNode node = find(root, key);
        return node != null;
    }

    private TreeNode<T> find(TreeNode<T> target, T key) {
        if (target == null) {
            return null;
        } else if (target.getValue().compareTo(key) == 0) {
            return target;
        } else if (target.getValue().compareTo(key) > 0) {
            return find(target.left, key);
        } else {
            return find(target.right, key);
        }
    }

    private TreeNode<T> findMaxValueNode(TreeNode<T> node) {
        if (node == null)
            return null;
        if (node.getRight() != null)
            return findMaxValueNode(node.getRight());
        return node;

    }


    static class TreeNode<T extends Comparable> {
        private TreeNode<T> left;
        private TreeNode<T> right;
        private int height;
        private T value;

        TreeNode(T value) {
            this.value = value;
        }

        TreeNode<T> getLeft() {
            return left;
        }

        void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        TreeNode<T> getRight() {
            return right;
        }

        void setRight(TreeNode<T> right) {
            this.right = right;
        }

        int getHeight() {
            return height;
        }

        void setHeight(int height) {
            this.height = height;
        }

        T getValue() {
            return value;
        }

        void setValue(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode<T> treeNode = (TreeNode<T>) o;
            return Objects.equals(value, treeNode.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
