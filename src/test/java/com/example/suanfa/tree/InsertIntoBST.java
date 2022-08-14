package com.example.suanfa.tree;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/6/2
 * @description:
 */
public class InsertIntoBST {

    @Test
    public void test(){

    }

    /**
     * BST中插入一个数
     * @param root
     * @param val
     * @return
     */
    private TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null) {
            return new TreeNode(val);
        }
        if(root.val == val) {
            return root;
        }
        if(val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        if(val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}