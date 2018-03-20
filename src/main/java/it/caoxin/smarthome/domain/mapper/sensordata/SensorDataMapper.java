package it.caoxin.smarthome.domain.mapper.sensordata;


import it.caoxin.smarthome.domain.model.SensorData;

public interface SensorDataMapper {
    int deleteById(Integer id);

    int insert(SensorData sensorData);

    int insertSelective(SensorData sensorData);

    SensorData selectById(Integer id);

    int updateByIdSelective(SensorData sensorData);

    int updateById(SensorData sensorData);
}