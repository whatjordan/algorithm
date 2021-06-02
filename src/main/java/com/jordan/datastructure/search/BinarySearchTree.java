package com.jordan.datastructure.search;

import java.util.Objects;
import java.util.Optional;

public class BinarySearchTree<T extends Comparable> {

    private TreeNode<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return;
        }
        TreeNode<T> target = root;
        while (true) {
            if (target.getValue().compareTo(value) == 0) {
                return;
            } else if (target.getValue().compareTo(value) > 0) {
                if (target.left == null) {
                    target.left = new TreeNode<>(value);
                    return;
                }
                target = target.left;
            } else {
                if (target.right == null) {
                    target.right = new TreeNode<>(value);
                    return;
                }
                target = target.right;
            }
        }
    }

    public void delete(T value) {
        if (root == null) {
            return;
        }
        TreeNode<T> targetParent = root;
        TreeNode<T> target = root;
        while (target != null) {
            if (target.getValue().compareTo(value) == 0) {
                break;
            } else if (target.getValue().compareTo(value) > 0) {
                targetParent = target;
                target = target.left;
            } else {
                targetParent = target;
                target = target.right;
            }
        }
        if (target == null) {
            return;
        }
        if (target.right == null && target.left == null) {
            if (target.getValue().equals(root.getValue())) {
                this.root = null;
            } else if (targetParent.getValue().compareTo(target.getValue()) > 0) {
                targetParent.left = null;
            } else {
                targetParent.right = null;
            }
        } else if (target.right == null) {
            target.setValue(Optional.ofNullable(target.left).map(TreeNode::getValue).orElse(null));
            target.left = Optional.ofNullable(target.left).map(l -> l.left).orElse(null);
            target.right = Optional.ofNullable(target.left).map(l -> l.right).orElse(null);
        } else if (target.left == null) {
            target.setValue(Optional.ofNullable(target.right).map(TreeNode::getValue).orElse(null));
            target.left = Optional.ofNullable(target.right).map(l -> l.left).orElse(null);
            target.right = Optional.ofNullable(target.right).map(l -> l.right).orElse(null);
        } else {
            TreeNode<T> preOrderParent = target;
            TreeNode<T> preOrder = target.left;
            while (preOrder.right != null) {
                preOrderParent = preOrder;
                preOrder = preOrder.right;
            }
            if (target.equals(preOrderParent)) {
                preOrderParent.left = preOrder.left;
            } else {
                preOrderParent.right = preOrder.left;
            }
            target.setValue(preOrder.getValue());
        }
    }

    public boolean contains(T key) {
        TreeNode node = find(root, key);
        return node != null;
    }

    public T max() {
        return Optional.ofNullable(max(this.root)).map(TreeNode::getValue).orElse(null);
    }

    private TreeNode<T> max(TreeNode<T> root) {
        TreeNode<T> max = root;
        while (max != null) {
            if (max.right != null) {
                max = max.right;
            } else {
                return max;
            }
        }
        return null;
    }

    public T min() {
        return Optional.ofNullable(min(this.root)).map(TreeNode::getValue).orElse(null);
    }

    public TreeNode<T> min(TreeNode<T> root) {
        TreeNode<T> min = root;
        while (min != null) {
            if (min.left != null) {
                min = min.left;
            } else {
                return min;
            }
        }
        return null;
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

    static class TreeNode<T extends Comparable> {
        private TreeNode<T> left;
        private TreeNode<T> right;
        private T value;

        TreeNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
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


