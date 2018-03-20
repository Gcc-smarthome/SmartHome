package it.caoxin.smarthome.domain.service.device.imp;

import it.caoxin.smarthome.domain.common.ClientIpPool;
import it.caoxin.smarthome.domain.common.DataOfCSHandler;
import it.caoxin.smarthome.domain.common.DataOfCSResult;
import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.service.SocketServer.client.EchoClient;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    FamilyMapper familyMapper;

    @Override
    public int deleteById(Integer id) {
        return deviceMapper.deleteById(id);
    }

    @Override
    public int insert(Device device) {
        return deviceMapper.insert(device);
    }

    @Override
    public int insertSelective(Device device) {

        return deviceMapper.insertSelective(device);
    }

    @Override
    public Device selectById(Integer id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(Device device) {
        return deviceMapper.updateByIdSelective(device);
    }

    @Override
    public int updateById(Device device) {
        return deviceMapper.updateById(device);
    }

    @Override
    public String initDevice(Integer familyId) {
        //先查找到对应的家庭中用户的设备
        Family family = familyMapper.selectById(familyId);
        List<Device> deviceList = family.getDeviceList();

        System.out.println("deviceList:"+deviceList);
        //可用DeviceList
        List<Device> onLiveDeviceList = new ArrayList<Device>();
        try {
            //通过id拿到对应的ip开始对客户端连接
            String familyIp = (String) ClientIpPool.getClientIpPoolMap().get(familyId);
            String host = familyIp;
            int port = 50001;
            EchoClient echoClient = new EchoClient(host, port,new DataOfCSResult(familyId,"init",null));
            echoClient.start();

            String serverData = echoClient.getDataOfCSResult().getReturnData();
            List<String> serverDeviceList = new DataOfCSHandler().parseJsonToList(serverData);

            for(int i = 0; i < serverDeviceList.size(); i++){
                String onLiveDevice = serverDeviceList.get(i);
                for(int j = 0; j < deviceList.size(); j++){
                    if (onLiveDevice.equals(deviceList.get(j).getName())){
                        onLiveDeviceList.add(deviceList.get(j));
                    }
                }
            }

            if(onLiveDeviceList != null){
                JSONArray jsonDeviceList= JSONArray.fromObject(onLiveDeviceList);
                System.out.println(deviceList);
                return jsonDeviceList.toString();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "No equipment available";

    }

    @Override
    public String operatorDevice(Integer familyId, String operator) {
        try {
            //通过id拿到对应的ip开始对客户端连接
            String familyIp = (String) ClientIpPool.getClientIpPoolMap().get(familyId);
            String host = familyIp;
            int port = 50001;

            EchoClient echoClient = new EchoClient(host, port,new DataOfCSResult(familyId,operator,null));
            echoClient.start();

            String serverData = echoClient.getDataOfCSResult().getReturnData();

            return serverData;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "the operator fail";
    }

    @Override
    public String getCanAddDevices() {
        Family family = familyMapper.selectByUniqueCode(Family.MANAGER_UNIQUE_CODE);
        List<Device> deviceList = family.getDeviceList();
        List<String> deviceNames = new ArrayList<String>();
        for (Device device:deviceList
             ) {
            deviceNames.add(device.getName());
        }

        return JSONObject.fromObject(deviceNames).toString();
    }

    @Override
    public String createDevice(Integer familyId, String deviceName) {
        Family family = familyMapper.selectByUniqueCode(Family.MANAGER_UNIQUE_CODE);
        List<Device> deviceList = family.getDeviceList();
        Device addDevice = null;
        for (Device device:deviceList
                ) {
           if (device.getName().equals(deviceName)){
               addDevice = device;
           }
        }
        if (addDevice != null){
            addDevice.setFamilyId(familyId);
            insert(addDevice);
            return "添加设备成功";
        }else {
            return "添加设备失败";
        }


    }
}
