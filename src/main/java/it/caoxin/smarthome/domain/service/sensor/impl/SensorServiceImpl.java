package it.caoxin.smarthome.domain.service.sensor.impl;

import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.service.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sensorService")
public class SensorServiceImpl implements SensorService {
    @Autowired
    private SensorMapper sensorMapper;
    @Override
    public int deleteById(Integer id) {
        return sensorMapper.deleteById(id);
    }

    @Override
    public int insert(Sensor sensor) {
        return sensorMapper.insert(sensor);
    }

    @Override
    public int insertSelective(Sensor sensor) {
        return sensorMapper.insertSelective(sensor);
    }

    @Override
    public Sensor selectById(Integer id) {
        return sensorMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(Sensor sensor) {
        return sensorMapper.updateByIdSelective(sensor);
    }

    @Override
    public int updateById(Sensor sensor) {
        return sensorMapper.updateById(sensor);
    }
}
