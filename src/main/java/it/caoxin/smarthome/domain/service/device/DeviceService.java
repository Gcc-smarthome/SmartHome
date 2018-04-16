package it.caoxin.smarthome.domain.service.device;

import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.bind.annotation.PathVariable;

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
    String operatorDevice(Integer familyId,Integer deviceId,String operator);

    /*
    管理员添加可用设备
     */
    String getCanAddDevices(User user, Family family);

    /*
    创建设备
     */
    String createDevice(Integer familyId,Integer userId,String deviceName);

    /*
    删除设备
     */
    String deleteDevice(Integer deviceId);
}
