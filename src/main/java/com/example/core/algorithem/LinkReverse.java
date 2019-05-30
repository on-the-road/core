package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/3/22 15:28
 * -描述- 链表逆序
 * -就地反转发
 * -思路：把当前链表的下一个节点插入到头结点dummy的下一个节点中，就地反转
 * - 写正确，真的不简单
 *
 */
public class LinkReverse {
    public static LinkNode LinkReverse(LinkNode head){
        if(head == null) return null;
        LinkNode dummy = new LinkNode(-1); //摆设节点,临时节点
        dummy.next = head;   //临时节点指针指向head节点

        //TODO 两个临时变量必不可少,记住这只是变量，仅此而已
        LinkNode pre = dummy.next; //当前节点,第一个节点(就是头节点)
        LinkNode current = pre.next; //需要反转的节点,(头结点的后继节点)往前移动

        while (current != null){ //需要反转的节点不是null
            //第一步 连接下一次需要反转的节点,本来连接的是current节点
            //todo 防止第三个节点丢失
            pre.next = current.next;

            //第二步 反转当前节点 -- current节点
            //todo 原来第一个节点的next指针指向 current，反转之后就是current（第二个节点）的next指针
            //todo 指向它前面的节点，即是第一个节点，也就是dummy.next
            //todo 注意这里第一个节点虽然是head，但是不能写成head，而是要写成 dummy.next
            current.next = dummy.next;

            //第三步 纠正dummy结点的指向
            //todo 这里是dummy节点，而不是头节点，此时current这个节点已变成头节点
            dummy.next = current;

            //第四步 当前节点指向下一次要反转的节点
            //todo 第一步中保存的节点，赋值给当前节点
            current = pre.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        //初始化一个链表

        LinkNode head = new LinkNode(0);
        LinkNode temp = new LinkNode(1);
        head.next = temp;
        for (int i = 2; i < 10; i++){
            temp.next = new LinkNode(i);
            temp = temp.next;
        }
        //反转前输出链表
        temp = head;
        System.out.println("反转前：");
        while (temp != null){
            System.out.print(temp.getData() + " ");
            temp = temp.next;
        }
        System.out.println();

        //执行反转链表
        LinkNode result = LinkReverse(head);

        //反转后输出链表
        temp = result;
        System.out.println("反转后：");
        while (temp != null){
            System.out.print(temp.getData() + " ");
            temp = temp.next;
        }


    }
}

class LinkNode{
    int data;
    LinkNode next;

    public LinkNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
