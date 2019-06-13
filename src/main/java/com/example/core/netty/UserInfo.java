package com.example.core.netty;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @Author wangwei
 * @Date 2019/6/1 14:49
 * -描述-
 */
public class UserInfo implements Serializable{

    private String userName;
    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * 手动序列化java对象 -so easy!
     */

    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        /**
         * buffer中的flip方法涉及到bufer中的Capacity,Position和Limit三个概念
         * 其中Capacity在读写模式下都是固定的，就是我们分配的缓冲大小
         * Position类似于读写指针，表示当前读(写)到什么位置
         * Limit在写模式下表示最多能写入多少数据，此时和Capacity相同,在读模式下表示最多能读多少数据
         * 此时和缓存中的实际数据大小相同
         * 在写模式下调用flip方法，那么limit就设置为了position当前的值(即当前写了多少数据)
         * postion会被置为0，以表示读操作从缓存的头开始读。也就是说调用flip之后
         * 读写指针指到缓存头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)
         * 注意：buffer.flip();一定得有，如果没有，就是从文件最后开始读取的，当然读出来的都是byte=0时候的字符
         */
        buffer.flip();
        value = null;
        /**
         * ByteBuffer.remaining() -- 返回ByteBuffer中实际数据的长度
         * 内部实现 --  return  limit - position
         */
        byte[] result = new byte[buffer.remaining()];
        /**
         * ByteBuffer.get(byte[])  --
         */
        buffer.get(result);
        return result;
    }

    public static void main(String[] args) throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(100);
        userInfo.setUserName("Welcome to Netty");

        //todo 使用java自带的序列化
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(bao);
        obj.writeObject(userInfo);
        obj.flush();
        obj.close();

        //todo userInfo 对象写入到了 obj-->bao中  -->转化为字节数组
        byte[] b = bao.toByteArray();

        System.out.println("------------------------------");
        System.out.println("The jdk serializable length is " + b.length);
        System.out.println("The byte array length is " + userInfo.codeC().length);
        System.out.println("------------------------------");

     }
}
