package it.caoxin.smarthome.domain.service.SocketServer.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import it.caoxin.smarthome.domain.socket.common.ChannelMap;
import it.caoxin.smarthome.domain.socket.socketmessage.BaseMsg;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author Administrator
 */ //因为服务器会响应传入的信息--->所以需要实现ChannelInboundHandler
@ChannelHandler.Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("终端系统接收到的指令:" + byteBuf.toString(CharsetUtil.UTF_8));
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        System.out.println(clientIP);
        //将接收到的消息写给发送者，而不冲刷入站的消息
        Random random = new Random();
        boolean b = random.nextBoolean();
        String result = null;

        System.out.println("result:"+result);
        System.out.println("b:"+b);
        if (b){
            result = "Success";
        }else {
            result = "failure";
        }

        ChannelMap.add(1,(SocketChannel) ctx.channel());
        ctx.writeAndFlush(Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));

        ReferenceCountUtil.release(byteBuf);
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
////        ctx.flush();//刷新后才将数据发出到SocketChannel
////        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印异常跟踪
        ctx.close();//关闭channel
    }
}
