package it.caoxin.smarthome.domain.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientIpPool {
    //<familyId,Ip>
    private static Map<Integer,String> ClientIpPoolMap = new HashMap<Integer, String>();

    public static Map getClientIpPoolMap() {
        return ClientIpPoolMap;
    }

}
