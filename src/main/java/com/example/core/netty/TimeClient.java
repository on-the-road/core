package com.example.core.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author wangwei
 * @Date 2019/5/30 14:29
 * -描述- Netty时间服务器客户端
 */
public class TimeClient {
    public void connect(int port,String host) throws Exception{
        //todo  类似于服务端的创建
        try (EventLoopGroup group = new NioEventLoopGroup()){
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //todo 发起异步连接操作,并同步阻塞住
            ChannelFuture future = b.connect(host,port).sync();
            //todo 等待客户端 链路关闭
            future.channel().closeFuture().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        new TimeClient().connect(port,"127.0.0.1");
    }
}
