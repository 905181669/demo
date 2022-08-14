package com.example.suanfa.tree;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/6/2
 * @description:
 */
public class ValidBST {

    //判断二叉搜索树是否合法：任意节点的值要大于等于左子树所有节点的值，小于等于右子树的所有节点的值
    @Test
    public  void test() {

        System.out.println(isValidBSTWrong(TreeUtil.buildTree()));
        System.out.println(isValidBST(TreeUtil.buildTree()));
    }

    // 错误的判断是否有效二叉搜索树
    private  boolean isValidBSTWrong(TreeNode root){
        if(root == null) {
            return true;
        }
        if(root.left != null && root.val <= root.left.val) {
            return false;
        }
        if(root.right != null && root.val >= root.right.val) {
            return false;
        }
        return isValidBSTWrong(root.left) && isValidBSTWrong(root.right);
    }


    // 正确的判断BST
    private  boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private  boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if(root == null){
            return true;
        }
        if(min != null && root.val <= min.val){
            return false;
        }
        if(max != null && root.val >= max.val){
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

}