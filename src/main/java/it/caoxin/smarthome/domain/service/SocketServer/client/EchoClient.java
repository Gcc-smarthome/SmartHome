package it.caoxin.smarthome.domain.service.SocketServer.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import it.caoxin.smarthome.domain.common.DataOfCSResult;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;
    //客户端服务器交换数据
    private DataOfCSResult dataOfCSResult;


    public EchoClient(String host, int port,DataOfCSResult dataOfCSResult) {
        this.host = host;
        this.port = port;
        this.dataOfCSResult = dataOfCSResult;
    }



    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        EchoClientHandler handler = new EchoClientHandler();


        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(handler);

                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
            //拿到服务器返回的数据
            dataOfCSResult.setReturnData(handler.getDataOfCSResult().getReturnData());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public DataOfCSResult getDataOfCSResult() {
        return dataOfCSResult;
    }

    public void setDataOfCSResult(DataOfCSResult dataOfCSResult) {
        this.dataOfCSResult = dataOfCSResult;
    }

}
