package it.caoxin.smarthome.domain.mapper.sensor;


import it.caoxin.smarthome.domain.model.Sensor;

import java.util.List;

public interface SensorMapper {
    int deleteById(Integer id);

    int insert(Sensor sensor);

    int insertSelective(Sensor sensor);

    Sensor selectById(Integer id);

    int updateByIdSelective(Sensor sensor);

    int updateById(Sensor sensor);

    List<Sensor> selectSensorByDeviceId(Integer deviceId);

    List<Sensor> getAllSensor();

    Integer getCount();
}