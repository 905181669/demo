package com.example.suanfa.tree;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/6/2
 * @description:
 */
public class InBST {

    @Test
    public void test(){
        System.out.println(isInBST(TreeUtil.buildTree(), 5));
    }

    public boolean isInBST(TreeNode root, int target){
        if(root == null) {
            return false;
        }
        if(target < root.val) {
            return isInBST(root.left, target);
        }else if(target > root.val) {
            return isInBST(root.right, target);
        }else {
            return true;
        }

    }
}