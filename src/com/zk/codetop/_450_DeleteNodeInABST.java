package com.zk.codetop;

import com.zk.domain.TreeNode;
import com.zk.utils.TreeUtils;

public class _450_DeleteNodeInABST {
    public static void main(String[] args) {
        Object[] root = {5,3,6,2,4,null,7};
        int key = 3;
        TreeUtils.preOrder(deleteNode(TreeUtils.createTree(root), key));
    }
    public static TreeNode deleteNode(TreeNode root, int key) {
        return findAndDeleteNode(root, key);
    }

    private static TreeNode findAndDeleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if(root.val == key){
            if(root.left == null && root.right == null){
                return null;
            }
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            TreeNode pre = root;
            TreeNode t = root.left;
            while(t.right != null){
                pre = t;
                t = t.right;
            }
            if(pre == root){
                pre.left = t.left;
            }else{
                pre.right = t.left;
            }
            root.val = t.val;
        }else{
            TreeNode left = findAndDeleteNode(root.left, key);
            TreeNode right = findAndDeleteNode(root.right, key);
            root.left = left;
            root.right = right;
        }
        return root;
    }
}














