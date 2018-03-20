package it.caoxin.smarthome.domain.common;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataOfCSHandler {
    public  String serverData;

    public  String getServerData() {
        return serverData;
    }

    public void setServerData(String serverData) {
        this.serverData = serverData;
    }

    public List<String> parseJsonToList(String serverData) {
        //从服务器拿到的设备数据
        ArrayList<String> deviceList = new ArrayList<String>();
        JSONObject jsonObject = JSONObject.fromObject(serverData);

        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){
            String next = (String) keys.next();
            String o = (String) jsonObject.get(next);
            deviceList.add(o);
        }
        System.out.println("deviceList:"+deviceList);
        return deviceList;

    }
}
