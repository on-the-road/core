package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2018/10/16 15:54
 * -描述- 二叉查找树
 */
public class BinarySearchNode {
    private int iDate; //数据域，可以存放对象
    private BinarySearchNode left;
    private BinarySearchNode right;

    public void displayNode(){
        System.out.println(iDate + "");
    }

    public BinarySearchNode(int iDate){
        this.iDate = iDate;
    }

    /**
     * 仔细体会这个方法...
     * @param iDate
     * @param root
     */
    public void insert(int iDate, BinarySearchNode root){ //插入新的节点
        BinarySearchNode node = new BinarySearchNode(iDate);
        if(root == null){ //树为空，把第一个节点置为根节点
            root = node;
        }else{ //不为空
            //声明两个指向root的引用
            BinarySearchNode current = root;
            BinarySearchNode parent = root;
            while (true){
                parent = current;
                if(iDate < current.iDate){ //待插入的数值小于当前节点的值
                    current = current.left; //把current指向当前节点的左孩子
                    if(current == null){
                        parent.left = node;
                        return;
                    }
                }else{ //待插入的数值大于当前节点的值
                    current = current.right;
                    if(current == null){
                        parent.right = node;
                        return;
                    }
                }
            }

        }
    }

    /**
     * 遍历是一个简单的递归过程，记住先序、中序、后序遍历的口诀就可以了
     * 先序，中序，后序都是以根节点来说的
     * @param node
     */
    public void preOrder(BinarySearchNode node){
        if(node != null){
            displayNode();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void midOrder(BinarySearchNode node){
        if(node != null){
            midOrder(node.left);
            displayNode();
            midOrder(node.right);
        }
    }

    public void lastOrder(BinarySearchNode node){
        if(node != null){
            lastOrder(node.left);
            lastOrder(node.right);
            displayNode();
        }
    }
}
