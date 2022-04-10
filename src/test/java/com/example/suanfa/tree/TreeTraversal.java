package com.example.suanfa.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 6/21/21 10:44:58
 * @description:
 *
 */
public class TreeTraversal {


    /**
     * 中序遍历
     * 解法2：迭代方式
     *
     * @param root
     * @return
     * 时间复杂度：O(n)O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     *
     * 空间复杂度：O(n)O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)O(n) 的级别。
     *
     *
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        while(root != null || !stk.isEmpty()){
            while(root != null){ //内循环一直将左节点入栈
                stk.push(root);
                root = root.left;
            }

            //外循环从栈中pop
            root = stk.pop();
            res.add(root.val);
            root = root.right;

        }
        return res;
    }


    /**
     * 前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }


    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }




}