package com.example.suanfa.tree;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 2021/12/11 07:45:44
 * @description: 树递归
 */
public class TreeDigui {

    private static TreeNode root;
    private static List<Integer> list = Lists.newArrayList();

    @Before
    public void before(){
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);

        root.right = new TreeNode(3);
    }
    /**
     * 树递归
     * 1.前序：中左右 (指处理顺序，前序遍历的处理顺序与访问顺序一致)
     * 2.中序：左中右
     * 3.后序：左右中
     */
    @Test
    public void treeDigui(){

        qianxu(root);
        System.out.println("前序：" + list);
        list.clear();

        qianxuUseStack(root);
        System.out.println("前序：" + list);

        list.clear();
        houxuUseStack(root);
        System.out.println("后序：" + list);

        list.clear();
        zhongxuUseStack(root);
        System.out.println("中序：" + list);

        list.clear();
        levelOrder(root);
        System.out.println("层序：" + list);

        Collections.reverse(list);
        System.out.println("层序倒序：" + list);
    }

    /**
     * 1.前序：中左右
     * @param root
     */
    private void qianxu(TreeNode root){
        if(root == null){
            return;
        }

        list.add(root.val);
        qianxu(root.left);
        qianxu(root.right);

    }

    /**
     * 1.中序：左中右
     * @param root
     */
    private void zhongxu(TreeNode root){
        if(root == null){
            return;
        }

        qianxu(root.left);
        list.add(root.val);
        qianxu(root.right);

    }

    /**
     * 前序：用栈实现,非递归方式
     * 前序遍历：遍历的顺序和处理的顺序是一致的，
     * 因此代码比较简洁，但是中序遍历的遍历顺序与处理顺序不一致，因此不能使用前序遍历的代码进行转换
     * @param root
     */
    private void qianxuUseStack(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){

            TreeNode node = stack.pop();
            if(node != null){
                list.add(node.val);
            }else {
                continue;
            }

            /**
             * 中左右
             */
            stack.push(node.right); //先push右
            stack.push(node.left); //再push左
        }
    }


    private void qianxuUseStack1(TreeNode root){
       Stack<TreeNode> stack = new Stack<>();
       stack.push(root);
       while (!stack.isEmpty()){
           TreeNode node = stack.pop();
           if(node != null){
               list.add(node.val);
           }else {
               continue;
           }


           stack.push(node.right);
           stack.push(node.left);
       }
    }


    /**
     * 后序：用栈实现,非递归方式
     * @param root
     */
    private void houxuUseStack(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){

            TreeNode node = stack.pop();
            if(node != null){
                list.add(node.val);
            }else {
                continue;
            }

            /**
             * 前序变换顺序，最后翻转结果列表就是后序遍历
             * 中右左---reverse--->左右中(后序)
             */
            stack.push(node.left); //先push左
            stack.push(node.right); //再push右

        }
        Collections.reverse(list);
    }


    /**
     * 中序：用栈实现,非递归方式
     * @param root
     */
    private void zhongxuUseStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                //当cur不为null，一路向左
                stack.push(cur);
                cur = cur.left;
            }else {
                //向左到了尽头，开始处理栈中的元素
                cur = stack.pop();
                //处理
                list.add(cur.val);
                //处理完当前访问的节点后，将cur指向右子节点
                cur = cur.right;

            }

        }
    }


    /**
     * 层序遍历（广度优先搜索）,利用队列的fifo
     * @param root
     */
    private void levelOrder(TreeNode root){

        LinkedList<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)queue.offer(node.left);
                if(node.right != null)queue.offer(node.right);
            }
        }

    }



    @Test
    public void test5() {

        int[] inorder = {1,2,3,4};
        int[] left = Arrays.copyOfRange(inorder, 0, inorder.length-1);
        System.out.println(Arrays.toString(left));


    }

    public TreeNode buildTree1(int[] inorder, int inLeft, int inRight,
                               int[] postorder, int postLeft, int postRight) {
        // 没有元素了
        if (inRight - inLeft < 1) {
            return null;
        }
        // 只有一个元素了
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }
        // 后序数组postorder里最后一个即为根结点
        int rootVal = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        // 根据根结点的值找到该值在中序数组inorder里的位置
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        // 根据rootIndex划分左右子树
        root.left = buildTree1(inorder, inLeft, rootIndex,
                postorder, postLeft, postLeft + (rootIndex - inLeft));
        root.right = buildTree1(inorder, rootIndex + 1, inRight,
                postorder, postLeft + (rootIndex - inLeft), postRight - 1);
        return root;
    }


    @Test
    public void testBuildTree() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = buildTree(inorder, postorder);
        System.out.println(root.val);

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0) {
            return null;
        }
        return traversal(inorder, postorder);
    }

    private TreeNode traversal(int[] inorder, int[] postorder) {
        int rootValue = postorder[postorder.length-1];
        TreeNode root = new TreeNode(rootValue);
        if(postorder.length == 1){
            return root;
        }
        //1.找到根节点值在中序数组中的位置
        int delimiterIndex;
        for(delimiterIndex = 0; delimiterIndex < inorder.length; delimiterIndex++ ){
            if(inorder[delimiterIndex] == rootValue){
                break;
            }
        }
        //2.切割中序数组
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, delimiterIndex);
        int[] rightInorder = Arrays.copyOfRange(inorder, delimiterIndex + 1, inorder.length);

        //3.切割后序数组
        postorder = Arrays.copyOfRange(postorder, 0, postorder.length-1);
        int [] leftPostorder = Arrays.copyOfRange(postorder, 0, leftInorder.length);
        int [] rightPostorder = Arrays.copyOfRange(postorder, leftInorder.length, postorder.length);

        root.left = traversal(leftInorder, leftPostorder);
        root.right = traversal(rightInorder, rightPostorder);

        return root;

    }




}