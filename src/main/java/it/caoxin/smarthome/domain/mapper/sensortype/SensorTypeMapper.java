package it.caoxin.smarthome.domain.mapper.sensortype;



import it.caoxin.smarthome.domain.model.SensorType;

import java.util.List;

public interface SensorTypeMapper {
    int deleteById(Integer id);

    int insert(SensorType sensorType);

    int insertSelective(SensorType sensorType);

    SensorType selectById(Integer id);

    int updateByIdSelective(SensorType sensorType);

    int updateById(SensorType sensorType);

    //管理员查询所有庭
    List<SensorType> getAllSensorType();

    //通过家庭名字模糊查询
    List<SensorType> getAllSensorTypeLikeName(String name);
}