package com.example.suanfa.dfs;

import com.example.suanfa.tree.TreeNode;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 8/4/21 11:34:31
 * @description:
 * 作者：wang_ni_ma
 *     链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/san-chong-jie-fa-xiang-xi-tu-jie-99-hui-fu-er-cha-/
 *
 *
 */
public class RecoverTree {

    TreeNode root;

    @Before
    public void setRoot(){
        root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
    }

    @Test
    public void testRecoverTree(){

        print(root);
        recoverTree(root);

        print(root);

    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        dfs(root,list);
        TreeNode x = null;
        TreeNode y = null;
        //扫面遍历的结果，找出可能存在错误交换的节点x和y
        for(int i=0;i<list.size()-1;++i) {
            if(list.get(i).val>list.get(i+1).val) {
                y = list.get(i+1);
                if(x==null) {
                    x = list.get(i);
                }
            }
        }
        //如果x和y不为空，则交换这两个节点值，恢复二叉搜索树
        if(x!=null && y!=null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    //中序遍历二叉树，并将遍历的结果保存到list中
    private void dfs(TreeNode node,List<TreeNode> list) {
        if(node==null) {
            return;
        }
        dfs(node.left,list);
        list.add(node);
        dfs(node.right,list);
    }


    private void print(TreeNode node){
        List<TreeNode> valList = Lists.newArrayList();
        dfs(node, valList);
        valList.stream().forEach(e-> System.out.print(e.val + "->"));
        System.out.println();

    }


    @Test
    public void testCombine(){
        int n = 4;
        int k = 2;
        System.out.println(combine(n, k));
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = Lists.newArrayList();
        if(k <= 0 || n < k){
            return result;
        }
        dfs(n, k, 1, new ArrayDeque(), result, 1);
        return result;
    }

    private void dfs(int n, int k, int start, Deque path, List<List<Integer>> result, int level){
        System.out.println("level=" + (level++));
        if(path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = start; i <= n; i++){
            path.addLast(i);
            dfs(n, k, i+1, path, result, level);
            path.removeLast();
        }
    }


    private void backtrace(int n, int k, int start, Deque<Integer> path, List<List<Integer>> result){

        if(path.size() == k){
            Iterator<Integer> it = path.iterator();
            int sum = 0;
            while(it.hasNext()){
                sum += it.next();
            }
            if(sum == n){
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for(int i = start; i <= 9; i++){
            path.addLast(i);
            backtrace(n, k, i+1, path, result);
            path.removeLast();
        }
    }




    @Test
    public void combinationSum(){
        int[] candidates = {2,3};
        System.out.println(combinationSum(candidates, 5));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = begin; i < len; i++){
            path.addLast(candidates[i]);
            dfs(candidates, begin, len, target - candidates[i], path, res);
            path.removeLast();
        }

        Set<Integer> set = new HashSet();
        int[] a = new int[set.size()];



    }

}