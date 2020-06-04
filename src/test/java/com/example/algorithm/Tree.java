package com.example.algorithm;

import com.google.common.collect.Lists;
import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: luozijian
 * @date: 2020-05-27 20:47:57
 * @description:
 */
public class Tree {


    public static void main(String[] args) {
        LinkedList<Integer> inputList =
                new LinkedList(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));

        TreeNode treeNode = createBinaryTree(inputList);

        System.out.println("前序遍历：");
//        preOrderTraveral(treeNode);

        System.out.println("栈前序遍历：");
//        preOrderTraveralWithStack(treeNode);

        Stack<String> strings = new Stack<>();
        strings.push("a");
        strings.push("b");
        System.out.println(strings.pop());


    }




    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){

        TreeNode node = null;
        if(inputList == null || inputList.isEmpty()){
            return null;
        }

        Integer data = inputList.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }


    /**
     * 前序遍历
     * @param node
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }

        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }


    /**
     * 中序
     * @param node
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }

        inOrderTraveral(node.leftChild);
        System.out.println(node.data);
        inOrderTraveral(node.rightChild);
    }


    /**
     * 后续
     * @param node
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }

        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        System.out.println(node.data);
    }


    /**
     * 利用栈非递归前序遍历
     * @param root
     */
    public static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()){
            //迭代访问节点的左孩子，并入栈
            while (treeNode != null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;

            }

            //如果节点没有左孩子，出栈，访问节点右孩子
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }

        }
    }


    /**
     * 层序遍历
     * @param root
     */
    public static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            TreeNode node = queue.poll();
            System.out.println(node.data);
            if(node.leftChild != null){
                queue.offer(node.leftChild);
            }

            if(node.rightChild != null){
                queue.offer(node.rightChild);
            }
        }
    }




    private static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
