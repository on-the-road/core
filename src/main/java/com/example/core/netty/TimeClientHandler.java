package com.example.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wangwei
 * @Date 2019/5/30 14:45
 * -描述- todo 该类类似于TimeServerHandler,处理网络事件的读写
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(TimeClientHandler.class);
    private int counter;
    private byte[] req;

    public TimeClientHandler(){
        req = ("QUERY TIEM ORDER" + System.getProperty("line.separator")).getBytes();

    }

    /**
     * todo 当客户端和服务端的链路建立成功之后，Netty的NIO线程会调用 channelActive方法
     * todo 发送查询事件的指令给服务端，调用ChannelHandlerContext的writeAndFlush将消息
     * todo 发送给服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf firstMessage = null;
        for (int i = 0; i < 100; i++){
            //todo 构造一个 ByteBuf
            firstMessage = Unpooled.buffer(req.length);
            //todo 把req写入到 ByteBuf
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        }
    }

    /**
     * todo 当服务端返回应答消息时，channelRead方法被调用，从ByteBuf中读取并打印应答消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf = (ByteBuf) msg;
        byte[] resp = new byte[buf.readableBytes()];
        //todo 把buf中的字节复制到字节数组 resp中
        buf.readBytes(resp);
        String body = new String(resp,"utf-8");*/
        String body = (String) msg;
        System.out.println("Now is:" + body + "; The counter is：" + ++counter);
    }

    /**
     * todo 发生异常，打印异常堆栈，关闭相关资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("Unexpected exception occured: {}",cause.getMessage());
        ctx.close();
    }
}
