package it.caoxin.smarthome.domain.service.device;

import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface DeviceService {
    int deleteById(Integer id);

    int insert(Device device);

    int insertSelective(Device device);

    Device selectById(Integer id);

    int updateByIdSelective(Device device);

    int updateById(Device device);

//    String initDevice(Integer familyId);
    /*
    控制设备
     */
    String operatorDevice(Integer familyId,Integer deviceId,String operator) throws TimeoutException, ExecutionException;

    /*
    管理员添加可用设备
     */
    String getCanAddDevices(User user, Family family);

    /*
    创建设备
     */
    String createDevice(Integer familyId,Integer userId,Integer deviceId);

    /*
    删除设备
     */
    String deleteDevice(Family family,User user,Integer deviceId);

    /*
    通过家庭id查询家庭设备
     */
    String getDeviceByFamilyId(Family family);

    /*
    通过设备id查询对应的设备详情
     */
    String getDeviceById(Device device);

    /*
    更新设备
     */
    String updateDevice(Device device);


}
