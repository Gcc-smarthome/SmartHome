package it.caoxin.smarthome.domain.service.SocketServer.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import it.caoxin.smarthome.domain.common.ClientIpPool;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 */ //因为服务器会响应传入的信息--->所以需要实现ChannelInboundHandler
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //将消息记录到控制台
        String clientIdAndIp = in.toString(CharsetUtil.UTF_8);
        System.out.println("Server received:" + clientIdAndIp);
        //将接收到的消息传递给ClientIpPool
        JSONObject jsonObject = JSONObject.fromObject(clientIdAndIp);
        String id = (String)jsonObject.get("id");
        String ip  = (String) jsonObject.get("ip");


        System.out.println("familyid:"+id);
        System.out.println("ip:"+ip);

        Map clientIpPool = ClientIpPool.getClientIpPoolMap();
        //获取来源ip
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        System.out.println(clientIP);

        clientIpPool.put(Integer.parseInt(id),clientIP);

        System.out.println("clientIpPool"+clientIpPool);

        ByteBuf resp = Unpooled.copiedBuffer("SYN".getBytes());
        ctx.write(resp);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();//刷新后才将数据发出到SocketChannel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//打印异常跟踪
        ctx.close();//关闭channel
    }
}
