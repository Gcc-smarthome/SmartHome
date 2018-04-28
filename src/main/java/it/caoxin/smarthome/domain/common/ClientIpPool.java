package it.caoxin.smarthome.domain.common;

import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientIpPool {
    //<familyId,Ip>
    private static Map<Integer,String> ClientIpPoolMap =
            new ConcurrentHashMap<Integer, String>();
    private static Map<Integer,SocketChannel> familyIpSocketMap =
            new ConcurrentHashMap<Integer,SocketChannel>();
//    <familyId,future>
    public static Map getClientIpPoolMap() {
        return ClientIpPoolMap;
    }
    public static Map getFamilyIpSocketMap(){return familyIpSocketMap;}

}
