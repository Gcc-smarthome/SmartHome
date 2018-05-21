package it.caoxin.SmartHomeServer.client;

import it.caoxin.SmartHomeServer.common.RpcRequest;
import it.caoxin.SmartHomeServer.common.RpcResponse;
import it.caoxin.SmartHomeServer.discovery.ServerDiscovery;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;


/**
 * RPC 代理（用于创建 RPC 服务代理）
 *
 */

public class SmartHomeRpcProxy {

	private String serverAddress;

	private ServerDiscovery serviceDiscovery;

	public SmartHomeRpcProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public SmartHomeRpcProxy(ServerDiscovery serviceDiscovery) {
		this.serviceDiscovery = serviceDiscovery;
	}

	//创建与服务端的请求代理
	public <T> T create(Class<?> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[] { interfaceClass }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
										 Object[] args) throws Throwable {
						//创建RpcRequest，封装被代理类的属性
						RpcRequest request = new RpcRequest();

						//设置请求id
						request.setRequestId(UUID.randomUUID().toString());

						//设置请求类名
						request.setClassName(method.getDeclaringClass()
								.getName());

						//设置方法名
						request.setMethodName(method.getName());
						System.out.println("method:"+method.getName());

						//设置请求参数类型
						request.setParameterTypes(method.getParameterTypes());
						System.out.println("method:"+method.getParameterTypes());

						//设置请求参数
						request.setParameters(args);

						//查找服务
						System.out.println("serverDiscovery:"+serviceDiscovery);
						if (serviceDiscovery != null) {
							serverAddress = serviceDiscovery.discover();

						}
						System.out.println("serverAddress："+serverAddress);
						//随机获取服务的地址
						String[] array = serverAddress.split(":");

						//主机名
						String host = array[0];

						//端口
						int port = Integer.parseInt(array[1]);

						//创建RpcClient，链接服务端
						SmartHomeRpcClient smartHomeRpcClient = new SmartHomeRpcClient(host, port);
						//通过netty实现RPC
						RpcResponse response = smartHomeRpcClient.send(request);
						//返回信息
						if (response.isError()) {
							throw response.getError();
						} else {
							return response.getResult();
						}
					}
				});
	}
}
