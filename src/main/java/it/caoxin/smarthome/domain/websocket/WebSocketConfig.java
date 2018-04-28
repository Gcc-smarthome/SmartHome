package it.caoxin.smarthome.domain.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 这个类表示启用websocket消息处理，以及收发消息的域
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         /*
             * 用户可以订阅来自"/topic"和"/sensor"的消息，
             * 在Controller中，可通过@SendTo注解指明发送目标，这样服务器就可以将消息发送到订阅相关消息的客户端
             *
             * 在本项目中，使用topic来达到群发效果，使用sensor进行一对一发送
             *
             * 客户端只可以订阅这两个前缀的主题
             */
       registry.enableSimpleBroker("/topic","/sensor");

       /*
        * 客户端发送过来的消息，需要以"/app"为前缀，再经过Broker转发给响应的Controller
        */
        registry.setApplicationDestinationPrefixes("/app");

          /*
         * 一对一发送的前缀
         * 订阅主题：/sensor/{sensorId}/add_Data/sensordata
         * 推送方式：1、@SendToUser("/add_Data/sensordata")
         *          2、messagingTemplate.convertAndSendToUser(destUsername, "/demo3/greetings", greeting);
         */
        registry.setUserDestinationPrefix("/sensor");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        /*
              * 路径"/webSocketEndPoint"被注册为STOMP端点，对外暴露，客户端通过该路径接入WebSocket服务
              */
        stompEndpointRegistry.addEndpoint("/webSocketEndPoint")
                .setAllowedOrigins("*").withSockJS();
    }
}
