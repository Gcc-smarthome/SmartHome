//package it.caoxin.smarthome.domain.socket.socketServer;
//
//
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.util.ReferenceCountUtil;
//import it.caoxin.smarthome.domain.socket.common.ChannelMap;
//import it.caoxin.smarthome.domain.socket.socketmessage.*;
//
//
//public class ServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        ChannelMap.remove((SocketChannel)ctx.channel());
//    }
//    @Override
//    protected void messageReceived(ChannelHandlerContext ctx, BaseMsg baseMsg) throws Exception {
//
//        switch (baseMsg.getType()){
//            case REQUEST:{
////                服务器接受到客户端登录请求
//                RequestMsg clientMsg = (RequestMsg) baseMsg;
//                RequestClientBody clientBody = (RequestClientBody) clientMsg.getBody();
//                String familyId = clientBody.getFamilyId();
//                String operatorInstruction = clientBody.getOperatorInstruction();
//                switch (clientBody.getOperatorInstruction()){
//                    case "CONNECT":{
//                        //当一个连接连入服务器时：首先判断该家庭中是否有这个对应的channel，如果则断开当前连接
//                        //如果没有则将该连接放入对应的ChannelMap中
//                        Channel channel = ChannelMap.get(Integer.parseInt(familyId));
//                        if(channel != null){
//                            ctx.channel().close();
//                        }else {
//                            ChannelMap.add(Integer.parseInt(familyId),(SocketChannel)ctx.channel());
//                        }
//                    }break;
//                    case "DISCONNECT":{
//                            ChannelMap.remove((SocketChannel)ctx.channel());
//                    }break;
//                    default:break;
//                }
//
////                NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyPing);
//            }break;
//            case RESPONSE:{
//                //收到客户端的请求
////                AskMsg askMsg=(AskMsg)baseMsg;
////                if("authToken".equals(askMsg.getParams().getAuth())){
////                    ReplyServerBody replyBody=new ReplyServerBody("server info $$$$ !!!");
////                    ReplyMsg replyMsg=new ReplyMsg();
////                    replyMsg.setBody(replyBody);
////                    NettyChannelMap.get(askMsg.getClientId()).writeAndFlush(replyMsg);
////                }
//            }break;
//            default:break;
//        }
//        ReferenceCountUtil.release(baseMsg);
//    }
//}
