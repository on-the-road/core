package com.example.core.test;

import java.util.*;

/**
 * @Author wangwei
 * @Date 2018/10/16 13:39
 * -描述-
 * huffman-tree(哈夫曼树又被称为最优二叉树)
 * 实现原理：
 * 由相同权值的一组叶子结点所构成的二叉树有不同的形态和不同的带权路径
 * 如何构造带权路径长度最小的二叉树，哈夫曼给出了答案，这就是著名的哈夫曼树
 * 构造huffman树的步骤如下: huffman算法
 * 1.根据给定的n个权值{w1...wn},构造n棵只有根节点的二叉树,令起权值为wj
 * 2.在森林中选取两颗根节点权值最小的树做左右子树,构造一棵新的二叉树，
 *  置新二叉树根节点的权值为两棵子树的根节点权值之和
 * 3.重复上述两步，直到只含一棵二叉树为止，这棵树即哈夫曼树
 */
public class Node<T> implements Comparable<Node<T>> {
    private T date; //节点数据
    private int weight; //权重
    private Node<T> left; //左孩子
    private Node<T> right; //右孩子

    public Node (T date,int weight) {
        this.date = date;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node<T> o) {
        if(o.weight > this.weight){
            return 1;
        }else if(o.weight < this.weight){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + date +
                ", weight=" + weight +
                '}';
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}

class HuffmanTree<T>{
    public static<T> Node<T> createTree(List<Node<T>> nodes){ //参数是多个节点组成的队列
        while (nodes.size() > 1){ //只剩一个节点时，退出循环
            /**
             * 1. list中的对象实现Comparable接口
             * 2. sort的重载方法 (Comparator作为参数)
             */
            Collections.sort(nodes); //排序，降序排列
            //取2个权重最小的节点
            Node<T> left = nodes.get(nodes.size() - 1);
            Node<T> right = nodes.get(nodes.size() - 2);
            //生成新节点，新节点权重=两个权重最小节点的和
            Node<T> parent = new Node<T>(null,left.getWeight() + right.getWeight());
            //原来的节点一处队列
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //取出唯一的节点
        return nodes.get(0);
    }

    /**
     * 这里不是很明白，对垒遍历出来有什么意义
     * @param root
     */
    public static void levelDisplay(Node root){ //使用对垒实现层次遍历
        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        //层次遍历
        while (! queue.isEmpty()){
            Node pNode = queue.poll();
            System.out.println(pNode.toString());
            if(pNode.getLeft() != null){
                queue.add(pNode.getLeft());
            }
            if(pNode.getRight() != null){
                queue.add(pNode.getRight());
            }
        }
    }

}

class HuffmanTreeTest{
    public static void main(String[] args){
        List<Node<String>> nodes = new ArrayList<>();
        nodes.add(new Node<String>("0-59", 45));
        nodes.add(new Node<String>("60-69", 42));
        nodes.add(new Node<String>("70-79", 60));
        nodes.add(new Node<String>("80-89", 100));
        nodes.add(new Node<String>("90-100", 75));

        Node<String> root = HuffmanTree.createTree(nodes);
        HuffmanTree.levelDisplay(root);

    }
}

