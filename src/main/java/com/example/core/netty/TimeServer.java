package com.example.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author wangwei
 * @Date 2019/5/30 11:05
 * -描述- Netty时间服务器服务端
 */
public class TimeServer {
    public void bind(int port) throws Exception {
        //todo 配置服务端的NIO线程组，recator线程组，用于处理网路事件
        //todo 这里有两个，一个是处理客户端的链接请求，一个是进行SocketChannel的网络读写

        //todo try with resources
        try (EventLoopGroup bossGroup = new NioEventLoopGroup();
             EventLoopGroup workerGroup = new NioEventLoopGroup()) {
            //todo NIO服务端 启动辅助类
            ServerBootstrap b = new ServerBootstrap();
            //todo 配置channel以及channel的tcp参数
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //todo 绑定IO事件的处理类,处理IO事件,日志，消息的编解码
                    .childHandler(new ChildChannelHandler());
            //todo 绑定端口，同步 等待成功
            ChannelFuture future = b.bind(port).sync();
            //todo 等待服务端监听端口 关闭 是为了阻塞main函数
            future.channel().closeFuture().sync();
        }
    }

    //todo private protected 可以修饰内部类
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            //todo 添加两个解码器类，解决tcp粘包问题
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            channel.pipeline().addLast(new StringDecoder());
            channel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        new TimeServer().bind(port);
    }


}

