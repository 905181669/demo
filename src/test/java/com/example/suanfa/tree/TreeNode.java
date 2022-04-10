package com.example.suanfa.tree;

/**
 * @author: luozijian
 * @date: 6/21/21 10:45:37
 * @description:
 */
public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;

      public TreeNode() {}

      public TreeNode(int val) { this.val = val; }


      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}