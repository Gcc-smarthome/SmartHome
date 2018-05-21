package it.caoxin.SmartHomeServer.client;

import com.rabbitmq.client.RpcClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import it.caoxin.SmartHomeServer.common.RpcDecoder;
import it.caoxin.SmartHomeServer.common.RpcEncoder;
import it.caoxin.SmartHomeServer.common.RpcRequest;
import it.caoxin.SmartHomeServer.common.RpcResponse;


//客户端发送Rpc请求
public class SmartHomeRpcClient extends SimpleChannelInboundHandler<RpcResponse> {


	private String host;
	private int port;

	private RpcResponse response;

	private final Object obj = new Object();

	public SmartHomeRpcClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 链接服务端，发送消息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public RpcResponse send(RpcRequest request) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel)
								throws Exception {
							// 向pipeline中添加编码、解码、业务处理的handler
							channel.pipeline()
									.addLast(new RpcEncoder(RpcRequest.class))
									.addLast(new RpcDecoder(RpcResponse.class))
									.addLast(SmartHomeRpcClient.this);
						}
					}).option(ChannelOption.SO_KEEPALIVE, true);
			// 链接服务器
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().writeAndFlush(request).sync();

			// 用线程等待的方式决定是否关闭连接
			synchronized (obj) {
				obj.wait();
			}
			if (response != null) {
				future.channel().closeFuture().sync();
			}
			return response;
		} finally {
			group.shutdownGracefully();
		}
	}

	/**
	 * 读取服务端的返回结果
	 */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, RpcResponse response)
			throws Exception {
		this.response = response;

		synchronized (obj) {
			obj.notifyAll();
		}
	}

	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
}
