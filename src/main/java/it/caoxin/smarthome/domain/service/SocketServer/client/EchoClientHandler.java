package it.caoxin.smarthome.domain.service.SocketServer.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import it.caoxin.smarthome.domain.common.DataOfCSResult;

import javax.xml.crypto.Data;

/**
 * @author Administrator
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private DataOfCSResult dataOfCSResult = new DataOfCSResult();
    //    建立时被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接服务器...");//familyId:operatorInstruction  1:cpt1
        String str = dataOfCSResult.getFamilyId()+":"+dataOfCSResult.getOperatorInstruction();
        byte[] req = str.getBytes();//消息
        ByteBuf firstMessage = Unpooled.buffer(req.length);//发送类
        firstMessage.writeBytes(req);//发送
        ctx.writeAndFlush(firstMessage);//flush
//       ctx.writeAndFlush(Unpooled.copiedBuffer("init_device:id:"+familyId, CharsetUtil.UTF_8));
    }

    //    每当接收数据时，都会调用这个方法。服务器接收到的数据可能会被分块接受
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received:"+byteBuf.toString(CharsetUtil.UTF_8));
        dataOfCSResult.setReturnData(byteBuf.toString(CharsetUtil.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public DataOfCSResult getDataOfCSResult() {
        return dataOfCSResult;
    }

    public void setDataOfCSResult(DataOfCSResult dataOfCSResult) {
        this.dataOfCSResult = dataOfCSResult;
    }
}
