package it.caoxin.smarthome.domain.service.device.imp;


import com.github.pagehelper.PageHelper;
import it.caoxin.SmartHomeServer.ClientApp.TerminalControlServices;
import it.caoxin.SmartHomeServer.client.SmartHomeRpcProxy;
import it.caoxin.SmartHomeServer.common.DataOfCSResult;
import it.caoxin.smarthome.domain.common.PageBean;
import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.mapper.deviceoperator.DeviceOperatorMapper;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.*;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


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
    UserMapper userMapper;

    @Autowired
    FamilyService familyService;


    @Autowired
    SmartHomeRpcProxy smartHomeRpcProxy;

    public static final Logger logger = LoggerFactory.getLogger("DeviceContoller");

    private static final String devicePhotoPath = "/upload/device/";
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

    //操作设备
    @Override
    public String operatorDevice(Integer familyId,Integer userId,Integer deviceId, String operator) {

        User user = userMapper.selectById(userId);

        String userPhone = null;
        if (user != null){
            userPhone = user.getPhone();
        }
        logger.info("control_device"+" "+familyId+" "+deviceId+" "+userPhone+" "+operator);

        TerminalControlServices helloService
                = smartHomeRpcProxy.create(TerminalControlServices.class);
         String result = helloService.controlDevice(new DataOfCSResult(familyId, deviceId, operator, null));

        if (result != null){
            return result;
        }

        return "the operator fail";
    }

    //获取可以添加的设备
    @Override
    public String getCanAddDevices(User user,Family family) {

        if (familyService.isFamilyManager(user,family)){
            Family rootFamily = familyMapper.selectByUniqueCode(Family.MANAGER_UNIQUE_CODE);
            List<Device> deviceList = rootFamily.getDeviceList();
            return JSONArray.fromObject(deviceList).toString();
        }
        return "can not get devices";
    }

    //创建设备
    @Override
    public String createDevice(Integer familyId,Integer userId, Integer deviceId) {


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
                        if (device.getId().equals(deviceId)){
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

    //删除设备
    @Override
    public String deleteDevice(Family family,User user,Integer deviceId) {
        if(familyService.isFamilyManager(user,family)){
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
        }else {
            return "you are not a manager";
        }


        return "delete Failure";
    }

    //通过家庭id查询设备
    @Override
    public String getDeviceByFamilyId(Family family) {
        if (family.getId() != null){
            List<Device> devices = deviceMapper.selectDeviceByFamilyId(family.getId());
            return JSONArray.fromObject(devices).toString();
        }
        return "searchDeviceFailure";
    }

    @Override
    public String getDeviceById(Device device) {
        if (device.getId() != null){
            Device selectDevice = deviceMapper.selectById(device.getId());
            return JSONArray.fromObject(selectDevice).toString();
        }
        return "find device failure";
    }

    @Override
    public String updateDevice(Device device) {
        if (device.getId() != null){
            updateByIdSelective(device);
            return "update success";
        }
        return "update failure";
    }

    @Override
    public String getAllDeive() {
        List<Device> deviceList = deviceMapper.getManagerDevice();
        return JSONArray.fromObject(deviceList).toString();
    }

    @Override
    public String fuzzyGetDevice(String name) {
        List<Device> deviceList = deviceMapper.fuzzyGetManagerDevice(name);
        return JSONArray.fromObject(deviceList).toString();
    }

    @Override
    public String getAllMemberDevice(Integer index) {
        PageBean<Device> pageBean = new PageBean<>();
        pageBean.setTotal(deviceMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<Device> allDevice = deviceMapper.getAllDevice();
        pageBean.setPage(index);
        pageBean.setBeanList(allDevice);

        JSONObject bean = JSONObject.fromObject(pageBean);
        return bean.toString();
    }

    @Override
    public String fuzzyGetAllMemberDevice(String name,Integer index) {
        PageBean<Device> pageBean = new PageBean<>();
        pageBean.setTotal(deviceMapper.fuzzyGetDevice(name).size());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<Device> devices = deviceMapper.fuzzyGetDevice(name);
        pageBean.setPage(index);
        pageBean.setBeanList(devices);

        JSONObject bean = JSONObject.fromObject(pageBean);

        return bean.toString();

    }

    @Override
    public String mDeleteDevice(Integer deviceId) {
        if (deviceId != null) {
            System.out.println("sensorId:"+deviceId);
            deviceMapper.deleteById(deviceId);
            return "delete Success";
        }
        return "id can not be null";
    }

    @Override
    public String mAddDevice(Device device) {
        if (device != null && device.getFamilyId() != null){
            insertSelective(device);
            return "add success";
        }
        return "add failure";
    }

    @Override
    public String uploadDevicePhoto(MultipartFile file, HttpServletRequest request) {
        //上传文件：
        String uploadPath = request.getServletContext().getRealPath("/upload/device");


        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID()+originalFilename.substring(originalFilename.length()-4,originalFilename.length());
        if (!originalFilename.endsWith(".jpg") &&
                !originalFilename.endsWith(".bmp") &&
                !originalFilename.endsWith(".jpeg")&&
                !originalFilename.endsWith(".png")){
            return "Illegal file format";
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath,fileName));
            System.out.print("上传路径是："+uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("添加图片出错。。。");
        }

        String photoUrl = devicePhotoPath + fileName;
        return photoUrl;
    }

    @Override
    public String getDevices() {
        List<Device> allDevice = deviceMapper.getAllDevice();
        return JSONArray.fromObject(allDevice).toString();
    }



}
