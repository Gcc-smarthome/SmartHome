package it.caoxin.smarthome.domain.service.deviceoperator;

import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.DeviceOperator;

public interface DeviceOperatorService {
    int deleteById(Integer id);

    int insert(DeviceOperator deviceOperator);

    int insertSelective(DeviceOperator deviceOperator);

    DeviceOperator selectById(Integer id);

    int updateByIdSelective(DeviceOperator deviceOperator);

    int updateById(DeviceOperator deviceOperator);

    /*
    管理员获取所有设备操作
     */
    String getAllDeviceOperator(Integer index);

    /*
    管理员通过设备Id获取设备操作
     */

    String getAllDeviceOperatorByDeviceId(Integer deviceId,Integer index);

    String mDeleteDeviceOperator(Integer deviceOperatorId);

    /*
    管理员添加设备操作
     */
    String  mAddDeviceOperator(DeviceOperator deviceOperator);
}
