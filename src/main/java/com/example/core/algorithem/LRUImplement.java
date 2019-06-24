package com.example.core.algorithem;

import java.util.HashMap;

/**
 * @Author wangwei
 * -描述- 实现一个简单的LRU算法，非线程安全的
 */
public class LRUImplement {
    private Node head;
    private Node end;
    private int limit; //缓存存储上线

    private final HashMap<String, Node> hashMap; //缓存容器

    /**
     * 初始化
     * @param limit
     */
    public LRUImplement(int limit){
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    /**
     *  获取节点
     * @param key
     * @return
     */
    public String get(String key){
        Node node = hashMap.get(key);
        if(node == null){
            return null;
        }
        refreshNode(node);
        return node.getValue();
    }

    /**
     * 插入节点
     * @param key
     * @param value
     */
    public void put(String key,String value){
        Node node = hashMap.get(key);
        if(node == null){
            //如果key不存在，则插入新的key-value
            if(hashMap.size() >= limit){ //判断 -- 容器已满
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key,value);
            //维护链表的结构
            addNode(node);
            //容器中存入node节点
            hashMap.put(key,node);
        }else{ //如果key存在，则刷新key-value
            node.setValue(value);
            refreshNode(node);
        }
    }

    /**
     * 刷新被访问的节点位置
     */

    public void refreshNode(Node node){
        //如果访问的是尾节点位置，则无需刷新
        if(node == end){
            return;
        }
        //移除节点
        removeNode(node);
        //重新插入节点
        addNode(node);
    }

    /**
     *  移除节点
     *  理解这里的 head和end 分别是头节点和为节点
     *  Node中的 pre和next 分别是前驱指针和后继指针
     */
    public String removeNode(Node node){
        if(node == end){ //如果删除的是尾节点
            end = node;
        }else if(node == head){ //如果删除的是头节点
            head = head.getNext();
        }else{ //如果删除的是中间节点,前驱和后继节点要改变指向....
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
        }
        return node.getKey();
    }

    /**
     * 尾部插入节点
     * 这里的逻辑需要思考下
     */
    public void addNode(Node node){
        if(end != null){
            end.setNext(node);
            node.setNext(null);
            node.setPre(end);
        }
        end = node;
        if(head == null){
            head = node;
        }
    }

    /**
     * 缓存删除节点方法，可能会用到
     */

    public void remove(String key){
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }
}

/**
 *  Node  -  节点类
 */
class Node{
    public Node(String key,String value){
        this.key = key;
        this.value = value;
    }
    private Node pre;
    private Node next;
    private String key;
    private String value;

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        LRUImplement lru = new LRUImplement(5);
        lru.put("001", "用户1信息");
        lru.put("002", "用户2信息");
        lru.put("003", "用户3信息");
        lru.put("004", "用户4信息");
        lru.put("005", "用户5信息");
        lru.get("002");
        lru.put("004", "用户4信息跟新");
        lru.put("006", "用户6信息");
        System.out.println(lru.get("001"));
        System.out.println(lru.get("006"));
        int a = 8/0;
    }









}
