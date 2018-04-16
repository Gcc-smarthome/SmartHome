package it.caoxin.smarthome.domain.service.device.imp;

import it.caoxin.smarthome.domain.common.ClientIpPool;
import it.caoxin.smarthome.domain.common.DataOfCSHandler;
import it.caoxin.smarthome.domain.common.DataOfCSResult;
import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.mapper.deviceoperator.DeviceOperatorMapper;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.model.*;
import it.caoxin.smarthome.domain.service.SocketServer.client.EchoClient;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceOperatorMapper deviceOperatorMapper;

    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    FamilyMapper familyMapper;

    @Autowired
    FamilyService familyService;

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

//    @Override
//    public String initDevice(Integer familyId) {
//        //先查找到对应的家庭中用户的设备
//        Family family = familyMapper.selectById(familyId);
//        List<Device> deviceList = family.getDeviceList();
//
//        System.out.println("deviceList:"+deviceList);
//        //可用DeviceList
//        List<Device> onLiveDeviceList = new ArrayList<Device>();
//        try {
//            //通过id拿到对应的ip开始对客户端连接
//            String familyIp = (String) ClientIpPool.getClientIpPoolMap().get(familyId);
//            String host = familyIp;
//            int port = 50001;
//            EchoClient echoClient = new EchoClient(host, port,new DataOfCSResult(familyId,"init",null));
//            echoClient.start();
//
//            String serverData = echoClient.getDataOfCSResult().getReturnData();
//            List<String> serverDeviceList = new DataOfCSHandler().parseJsonToList(serverData);
//
//            for(int i = 0; i < serverDeviceList.size(); i++){
//                String onLiveDevice = serverDeviceList.get(i);
//                for(int j = 0; j < deviceList.size(); j++){
//                    if (onLiveDevice.equals(deviceList.get(j).getName())){
//                        onLiveDeviceList.add(deviceList.get(j));
//                    }
//                }
//            }
//
//            if(onLiveDeviceList != null){
//                JSONArray jsonDeviceList= JSONArray.fromObject(onLiveDeviceList);
//                System.out.println(deviceList);
//                return jsonDeviceList.toString();
//
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return "No equipment available";
//
//    }
//
    @Override
    public String operatorDevice(Integer familyId,Integer deviceId, String operator) {
        System.out.println("faimlyId："+familyId);
        System.out.println("deviceId："+deviceId);
        System.out.println("operator："+operator);
        try {
            //通过id拿到对应的ip开始对客户端连接
            String familyIp = (String) ClientIpPool.getClientIpPoolMap().get(familyId);
            String host = familyIp;
            int port = 50001;

            EchoClient echoClient = new EchoClient(host, port,new DataOfCSResult(familyId,operator,deviceId,null));
            echoClient.start();

            String serverData = echoClient.getDataOfCSResult().getReturnData();

            return serverData;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "the operator fail";
    }

    @Override
    public String getCanAddDevices(User user,Family family) {

        if (familyService.isFamilyManager(user,family)){
            Family rootFamily = familyMapper.selectByUniqueCode(Family.MANAGER_UNIQUE_CODE);
            List<Device> deviceList = rootFamily.getDeviceList();
            return JSONArray.fromObject(deviceList).toString();
        }
        return "can not get devices";
    }

    @Override
    public String createDevice(Integer familyId,Integer userId, String deviceName) {


        if (familyId != null && userId != null){
            Family family = new Family();
            family.setId(familyId);

            User user = new User();
            user.setId(userId);

            if (familyService.isFamilyManager(user,family)){
                Family rootFamily = familyMapper.selectByUniqueCode(Family.MANAGER_UNIQUE_CODE);

                if (family != null){
                    List<Device> deviceList = rootFamily.getDeviceList();
                    System.out.println("deviceList:"+deviceList);
                    Device addDevice = null;
                    int rootDeviceId = 0;
                    for (Device device:deviceList
                            ) {
                        if (device.getName().equals(deviceName)){
                            rootDeviceId = device.getId();
                            addDevice = device;
                            addDevice.setId(null);

                        }
                    }
                    if (addDevice != null){
                        //添加设备
                        addDevice.setFamilyId(familyId);
                        insert(addDevice);

                        //添加设备操作
                        List<DeviceOperator> deviceOperators = deviceOperatorMapper.selectOperatorByDeviceId(rootDeviceId);
                        List<Sensor> sensors = sensorMapper.selectSensorByDeviceId(rootDeviceId);
                        //添加对应用户的设备操作
                        for (DeviceOperator deviceOperator:deviceOperators
                             ) {
                            DeviceOperator newDeviceOperator = deviceOperator;
                            newDeviceOperator.setId(null);
                            newDeviceOperator.setDeviceId(addDevice.getId());

                           deviceOperatorMapper.insertSelective(newDeviceOperator);

                        }

                        //对应用户添加传感器
                        for (Sensor sensor:sensors
                             ) {
                            Sensor newSensor = sensor;
                            newSensor.setId(null);
                            newSensor.setDeviceId(addDevice.getId());

                            sensorMapper.insertSelective(newSensor);
                        }

                        //添加对应的设备的传感器

                        return "add device success";
                    }else {
                        return "add device failure";
                    }
                }
            }
        }

        return "can not add device";

    }

    @Override
    public String deleteDevice(Integer deviceId) {
        if (deviceId != null){
            //删除对应的设备传感器
            List<DeviceOperator> deviceOperators = deviceOperatorMapper.selectOperatorByDeviceId(deviceId);
            for (DeviceOperator deviceOperator:deviceOperators
                 ) {
                deviceOperatorMapper.deleteById(deviceOperator.getId());
            }
            //删除对应的设备操作
            List<Sensor> sensors = sensorMapper.selectSensorByDeviceId(deviceId);
            for (Sensor sensor:sensors
                 ) {
                sensorMapper.deleteById(sensor.getId());
            }
            //删除对应的设备
            deviceMapper.deleteById(deviceId);

            return "delete Success";
        }

        return "delete Failure";
    }
}
