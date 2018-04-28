package it.caoxin.smarthome.domain.service.SocketServer.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {

    }

    public void start() {
        //定义服务器处理类
        final EchoServerHandler echoServerHandler = new EchoServerHandler();

        //测试
//        ServerTestHandler handler = new ServerTestHandler();

        //创建服务器引导类
        ServerBootstrap serverBoot = new ServerBootstrap();

        //创建EventLoopGroup
        /**
         * 一个EventLoop可以处理很多个Channel
         * EventLoopGroup可以含有很多个EventLoop
         * 可以通过一种迭代的方式找出下一个需要处理的清单中的下一个EventLoop
         */
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            serverBoot.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        /**
                         * channelHandler是如何安装在pipeline上面的？
                         * channelHandler实现了ChannelInitializer:ChannelInitializer通过SeverBootstrap进行注册
                         * 1.ChannelInitalizer首先集合所有的handler,当调用InitChannel()的时候，ChannelInitalizer
                         * 将所有集合在他身上的handler集合到pipeLine中
                         * 2.操作完成后，ChannelInitalizer将自己的所有handler删除。
                         *
                         */
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });
            serverBoot.option(ChannelOption.SO_BACKLOG, 0);
            //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
            serverBoot.option(ChannelOption.TCP_NODELAY, true);;
//            异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            serverBoot.childOption(ChannelOption.SO_KEEPALIVE, true);
//            异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = serverBoot.bind().sync();
//            获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } finally {
//            关闭EventLoopGroup
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
