package it.caoxin.smarthome.domain.socket.common;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class ChannelMap {
    private static Map<Integer,SocketChannel> map=new ConcurrentHashMap<Integer, SocketChannel>();
    public static void add(Integer familyId,SocketChannel socketChannel){
        map.put(familyId,socketChannel);
    }
    public static Channel get(Integer familyId){
       return map.get(familyId);
    }
    public static void remove(SocketChannel socketChannel){
        for (Map.Entry entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
                map.remove(entry.getKey());
            }
        }
    }

}
