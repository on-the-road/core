package com.example.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @Author wangwei
 * @Date 2019/5/30 11:38
 * -描述- todo 该类用于对网络事件进行读写操作，通常我们只需要关注 channelRead() 和 exceptionCaught()
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       /* //todo 处理请求数据
        ByteBuf buf = (ByteBuf) msg;
        //todo buf.readableBytes 获取缓冲区中的可读的字节数
        byte[] req = new byte[buf.readableBytes()];
        //todo 将buf缓冲区中的字节数组复制到新建的byte[]
        buf.readBytes(req);
        String body = new String(req,"utf-8")
                .substring(0,req.length - System.getProperty("line.separator").length());*/
        String body = (String) msg;
        System.out.println("The TimeServer receive order:" + body + "; the counter is:" + ++counter);
        String currentTime = "QUERY TIEM ORDER".equalsIgnoreCase(body) ?
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");
        //todo  响应客户端
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //todo 异步发送消息给客户端
        ctx.write(resp);
    }

    /**
     * todo 将消息队列中的消息写入到SocketChannel中发送给对方
     * todo 出于性能考虑，write方法并不直接将消息写入到SocketChannel
     * todo 调用write方法只是将待发送消息放到发送缓冲数组中，调用flush方法将发送缓冲区中的消息全部写入到SocketChannel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * todo 发生异常，关闭ChannelHandlerContext，释放相关的资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
