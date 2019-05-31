package com.example.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @Author wangwei
 * @Date 2018/10/19 11:28
 * -描述- 搭建一个netty服务器
 */
public class HttpServer {
    private final int port;

    public HttpServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
       /* if(args.length != 1){
            System.err.println("usage:"+ HttpServer.class.getSimpleName()+"<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);*/
        int port = 3456;
        new HttpServer(port).start();

    }

    public void start()throws Exception{
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup nelg = new NioEventLoopGroup();
        bootstrap.group(nelg).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        System.out.println("initChannel:"+ channel);
                        channel.pipeline()
                                .addLast("decoder",new HttpRequestDecoder())
                                .addLast("encoder",new HttpResponseEncoder())
                                .addLast("aggregator",new HttpObjectAggregator(512*1024))
                                .addLast("handler",new HttpHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE);
        bootstrap.bind(port).sync();

    }
}
