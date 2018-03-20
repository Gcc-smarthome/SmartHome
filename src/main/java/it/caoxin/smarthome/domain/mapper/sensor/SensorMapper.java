package it.caoxin.smarthome.domain.mapper.sensor;


import it.caoxin.smarthome.domain.model.Sensor;

public interface SensorMapper {
    int deleteById(Integer id);

    int insert(Sensor sensor);

    int insertSelective(Sensor sensor);

    Sensor selectById(Integer id);

    int updateByIdSelective(Sensor sensor);

    int updateById(Sensor sensor);
}