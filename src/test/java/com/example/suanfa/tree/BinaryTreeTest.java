package com.example.suanfa.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 3/7/21 10:52:22
 * @description:
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
//        Node root = createNode();

//        preOrder(root);

//        inOrder(root);

//        postOrder(root);

        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);

//        System.out.println(isValidBST(root));

//        inOrder(root);

//        postOrder(root);


        preOrder(root);
        System.out.println();
        preOrder1(root);


    }



    public static TreeNode createNode(){

        TreeNode left = new TreeNode(1, null, null);
        TreeNode right = new TreeNode(2, null, null);

        TreeNode root = new TreeNode(3, left, right);

        return root;
    }


    /**
     * 前序遍历(根-左-右)
     * @param node
     */
    public static void preOrder(TreeNode node){
        if(node == null){
            return;
        }

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);

    }


    /**
     * 非递归
     * @param root
     */
    public static void preOrder1(TreeNode root) {
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){

            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }


        }
    }



    /**
     * 中序遍历(左-根-右)
     * 中序遍历二叉搜索树，结果是有序的
     * @param node
     */
    public static void inOrder(TreeNode node){
        if(node == null){
            return;
        }


        inOrder(node.left);

        System.out.print(node.val + " ");

        inOrder(node.right);

    }


    /**
     * 后序遍历(左-右-根)
     * @param node
     */
    public static void postOrder(TreeNode node){
        if(node == null){
            return;
        }

        postOrder(node.left);

        postOrder(node.right);


        System.out.print(node.val + " ");

    }


    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        //创建一个队列
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int count = 0;
        while (!deque.isEmpty()) {
            //每一层的个数
            int size = deque.size();
            while (size-- > 0) {
                TreeNode cur = deque.pop();
                if (cur.left != null)
                    deque.addLast(cur.left);
                if (cur.right != null)
                    deque.addLast(cur.right);
            }
            count++;
        }
        return count;
    }


    @Test
    public void test(){
        Deque<Integer> deque = new LinkedList<>();


        deque.addFirst(1);
        deque.addFirst(2);




        System.out.println(deque);


        System.out.println(deque.pop());
    }


    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null)
            return true;
        //每个节点如果超过这个范围，直接返回false
        if (root.val >= maxVal || root.val <= minVal)
            return false;
        //这里再分别以左右两个子节点分别判断，
        //左子树范围的最小值是minVal，最大值是当前节点的值，也就是root的值，因为左子树的值要比当前节点小
        //右子数范围的最大值是maxVal，最小值是当前节点的值，也就是root的值，因为右子树的值要比当前节点大
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }




    @Test
    public void testIsValidBST1(){
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        System.out.println(isValidBST1(root));

    }
    //前一个结点，全局的
    static TreeNode prev;

    public static boolean isValidBST1(TreeNode root) {
        if (root == null)
            return true;
        //访问左子树
        if (!isValidBST1(root.left))
            return false;
        //访问当前节点：如果当前节点小于等于中序遍历的前一个节点直接返回false。
        if (prev != null && prev.val >= root.val)
            return false;

        prev = root;
        //访问右子树
        if (!isValidBST1(root.right))
            return false;
        return true;
    }

}



