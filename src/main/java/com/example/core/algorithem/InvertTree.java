package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/8/1 11:37
 * -描述- 反转 二叉树，根节点不动，左右子节点互换
 * - homebrew mac上的包管理器
 */
public class InvertTree {
    public TreeNode2 invertTree(TreeNode2 root){
        if(root == null) return null;
        //todo 递归的思想完美体现
        TreeNode2 left = invertTree(root.left);
        TreeNode2 right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

}
class TreeNode2{
    TreeNode2 left;
    TreeNode2 right;
    private int val;
}
