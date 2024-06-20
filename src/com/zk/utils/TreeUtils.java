package com.zk.utils;


import com.zk.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeUtils {
    private TreeUtils(){}
    //层次遍历构造二叉树
    public static TreeNode createTree(Object[] arr){
        if(arr.length == 0){
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode((int)arr[0]);
        queue.offer(root);

        int i = 1;
        TreeNode node;
        while(!queue.isEmpty() && i < arr.length){

            if(queue.peek() != null){
                node = queue.poll();
                if(arr[i] == null){
                    node.left = null;
                    i++;
                }else{
                    node.left = new TreeNode((int)arr[i++]);
                    queue.offer(node.left);
                }
                if(i == arr.length){
                    break;
                }
                if(arr[i] == null){
                    node.right = null;
                    i++;
                }else{
                    node.right = new TreeNode((int)arr[i++]);
                    queue.offer(node.right);
                }
            }

        }
        return root;
    }
    //求树的高度
    public static int depth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    //树的前序遍历
    public static void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
}
