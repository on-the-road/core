package com.example.core.netty.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @Author wangwei
 * @Date 2019/6/3 10:45
 * -描述-
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final int length = byteBuf.readableBytes();
        final byte [] array = new byte[length];
        //todo ByteBuf.readerIndex() -- 获取读指针的索引
        /**
         * ByteBuf是byte数组的缓冲区，通过两个指针完成读写操作，readerIndex、writeIndex
         * 两个的初始取值均为零，读取数据时 readerIndex增加，写入数据时writeIndex增加
         * clear()方法，清零
         *
         */
        //todo ByteBuf.getBytes() --
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        MessagePack msgPack = new MessagePack();
        list.add(msgPack.read(array));
    }
}
