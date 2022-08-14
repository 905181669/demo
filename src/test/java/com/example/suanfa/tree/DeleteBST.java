package com.example.suanfa.tree;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/6/3
 * @description:
 */
public class DeleteBST {

    @Test
    public void test() {

    }

    private TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }

        if(root.val == key){
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }

            // 情况3：待删除节点的左右子节点都不为null，那么就要在右子树中找到最小的节点来替代自己
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);

        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else if(root.val < key){
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node){
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}