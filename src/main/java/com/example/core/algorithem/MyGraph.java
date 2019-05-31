package com.example.core.algorithem;

import java.util.LinkedList;

/**
 * @Author wangwei
 * @Date 2019/5/31 13:57
 * -描述-
 */
public class MyGraph {
    /**
     * todo 图的顶点
     */
    private class Vertex{
        int data;
        Vertex(int data){
            this.data = data;
        }
    }


    //todo 图邻接表形式
    private int size;
    private Vertex [] vertexes;
    //todo 这是一个链表的数组形式
    private LinkedList<Integer>[] obj;

    public MyGraph(int size){
        this.size = size;
        //初始化顶点和邻接表
        vertexes = new Vertex[size];
        obj = new LinkedList[size];
        for (int i = 0; i < size; i++){
            vertexes[i] = new Vertex(i);
            obj[i] = new LinkedList<>();
        }
    }

    /**
     * 深度优先遍历
     */

    public void dfs(MyGraph graph,int start,boolean[] visited){
        System.out.println("起始位置：" + graph.vertexes[start].data);
        visited[start] = true;
        //todo 循环start顶点所对应的链表,链表中存放的是该顶点可以直接到达的其他顶点
        for (int index:graph.obj[start]){
            //todo 类似于一个递归的操作
            if(!visited[index]){
                dfs(graph,index,visited);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void bfs(MyGraph graph,int start,boolean[] visited,LinkedList<Integer> queue){
        //todo linkedList.offer -- 默认tail尾部添加元素
        queue.offer(start);
        while (!queue.isEmpty()){
            //todo queue.poll() -- remove and display
            int front = queue.poll();
            if(visited[front]) continue;
            System.out.println("起始位置：" + graph.vertexes[front].data);
            visited[front] = true;
            for (int index: graph.obj[front]){
                queue.offer(index);
            }
        }
    }

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(6);
        graph.obj[0].add(1);
        graph.obj[0].add(2);
        graph.obj[0].add(3);

        graph.obj[1].add(0);
        graph.obj[1].add(3);
        graph.obj[1].add(4);

        graph.obj[2].add(0);

        graph.obj[3].add(0);
        graph.obj[3].add(1);
        graph.obj[3].add(4);
        graph.obj[3].add(5);

        graph.obj[4].add(1);
        graph.obj[4].add(3);
        graph.obj[4].add(5);

        graph.obj[5].add(3);
        graph.obj[5].add(4);

        System.out.println("图的深度优先遍历：");
        graph.dfs(graph,0,new boolean[graph.size]);
        System.out.println("图的广度优先遍历：");
        graph.bfs(graph,0,new boolean[graph.size],new LinkedList<Integer>());


    }

}
