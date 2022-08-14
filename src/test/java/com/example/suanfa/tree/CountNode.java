package com.example.suanfa.tree;

/**
 * @author: luozijian
 * @date: 2022/6/3
 * @description: 计算节点数
 */
public class CountNode {

    /**
     * 普通计算树节点的方法
     * @param root
     * @return
     */
    public int countNodes(TreeNode root){
        if(root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    /**
     * 满二叉树计算节点的方法
     * @param root
     * @return
     */
    public int countPerfectTreeNodes(TreeNode root){
        int h = 0;
        while(root != null) {
            root = root.left;
            h++;
        }
        return (int)Math.pow(2, h) - 1;
    }

    /**
     * 计算完全二叉树节点的方法
     * @param root
     * @return
     */
    public int countCompleteTreeNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int hr = 0, hl = 0;
        TreeNode l = root, r = root;
        while (l !=  null){
            l = l.left;
            hl++;
        }
        while (r != null){
            r = r.right;
            hr++;
        }
        if(hr == hl){
            return countPerfectTreeNodes(root);
        }
        return countNodes(root);
    }

}