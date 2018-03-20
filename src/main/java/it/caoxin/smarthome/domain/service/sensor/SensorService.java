package it.caoxin.smarthome.domain.service.sensor;

import it.caoxin.smarthome.domain.model.Sensor;

public interface SensorService {
    int deleteById(Integer id);

    int insert(Sensor sensor);

    int insertSelective(Sensor sensor);

    Sensor selectById(Integer id);

    int updateByIdSelective(Sensor sensor);

    int updateById(Sensor sensor);
}
