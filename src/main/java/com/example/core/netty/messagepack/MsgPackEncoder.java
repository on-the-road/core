package com.example.core.netty.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Author wangwei
 * @Date 2019/6/3 10:36
 * -描述- 编写 message-pack编码器
 */
public class MsgPackEncoder extends MessageToByteEncoder<Object>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack pack = new MessagePack();
        //todo messagepack负责将Object类型的pojo编码为字节数组
        byte[] raw = pack.write(o);
        //todo 将字节数组写入到 ByteBuf中
        byteBuf.writeBytes(raw);
    }
}
