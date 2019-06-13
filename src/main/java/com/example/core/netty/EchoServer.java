package com.example.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author wangwei
 * @Date 2019/5/31 17:21
 * -描述-
 */
public class EchoServer {
    public void bind(int port){
        try (EventLoopGroup bossGroup = new NioEventLoopGroup();
             EventLoopGroup workerGroup = new NioEventLoopGroup()){
             ServerBootstrap b = new ServerBootstrap();
             b.group(bossGroup,workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .option(ChannelOption.SO_BACKLOG,100)
                     .handler(new LoggingHandler(LogLevel.INFO))
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel channel) throws Exception {
                             ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                             channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                             channel.pipeline().addLast(new StringDecoder());
                             channel.pipeline().addLast(new EchoServerHandler());
                         }
                     });
            //todo 绑定端口 同步等待成功
            ChannelFuture future = b.bind(port).sync();
            //todo 等待服务器监听端口关闭
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new EchoServer().bind(port);
    }
}
