package it.caoxin.smarthome.domain.service.sensordata;

import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.model.SensorData;

public interface SensorDataService {
    int deleteById(Integer id);

    int insert(SensorData sensorData);

    int insertSelective(SensorData sensorData);

    SensorData selectById(Integer id);

    int updateByIdSelective(SensorData sensorData);

    int updateById(SensorData sensorData);

    String getSensorsData(Integer sensorId);
}
