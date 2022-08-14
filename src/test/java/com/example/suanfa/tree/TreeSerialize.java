package com.example.suanfa.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author: luozijian
 * @date: 2022/6/3
 * @description: 树的序列化
 */
public class TreeSerialize {

    @Test
    public void test(){
        System.out.println(serialize(TreeUtil.buildTree()));
    }

    public String serialize(TreeNode root){
        StringBuilder sb = new StringBuilder();
        doSerialize(root, sb);
        return sb.toString();
    }

    private void doSerialize(TreeNode root, StringBuilder sb) {
        if(root == null){
            sb.append("#");
            sb.append(",");
            return;
        }
        sb.append(root.val);
        sb.append(",");

        doSerialize(root.left, sb);
        doSerialize(root.right, sb);
    }


    public TreeNode deserialize(String data){
        String[] datas = data.split(",");
        LinkedList<String> list = new LinkedList<>();
        Arrays.stream(datas).forEach(e -> list.add(e));
        return deserialize(list);
    }
    private TreeNode deserialize(LinkedList<String> nodes) {
        String first = nodes.removeFirst();
        if(first.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(first));
        node.left = deserialize(nodes);
        node.right = deserialize(nodes);
        return node;
    }
}