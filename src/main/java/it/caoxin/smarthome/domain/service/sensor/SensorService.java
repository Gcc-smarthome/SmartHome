package it.caoxin.smarthome.domain.service.sensor;

import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.model.SensorType;

import java.util.List;

public interface SensorService {
    int deleteById(Integer id);

    int insert(Sensor sensor);

    int insertSelective(Sensor sensor);

    Sensor selectById(Integer id);

    int updateByIdSelective(Sensor sensor);

    int updateById(Sensor sensor);

    /*
    获取所有传感器
     */
    String getAllSensor(Integer index);

    /*
    通过设备id查询传感器
     */
    String getSensorByDeviceId(Integer deviceId,Integer index);

    String mDeleteSensor(Integer sensorId);

    String mAddSensor(Sensor sensor);

}
