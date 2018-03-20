package it.caoxin.smarthome.domain.mapper.sensortype;


import it.caoxin.smarthome.domain.model.SensorType;

public interface SensorTypeMapper {
    int deleteById(Integer id);

    int insert(SensorType sensorType);

    int insertSelective(SensorType sensorType);

    SensorType selectById(Integer id);

    int updateByIdSelective(SensorType sensorType);

    int updateById(SensorType sensorType);
}