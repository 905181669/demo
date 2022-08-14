package com.example.suanfa.tree;

/**
 * @author: luozijian
 * @date: 2022/6/2
 * @description:
 */
public class TreeUtil {

    public static TreeNode buildBSTTree(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);

        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static TreeNode buildTree(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);


        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(20);
        return root;
    }
}