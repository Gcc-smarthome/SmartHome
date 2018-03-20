package it.caoxin.smarthome.domain.service.sensortype.impl;


import it.caoxin.smarthome.domain.mapper.sensortype.SensorTypeMapper;
import it.caoxin.smarthome.domain.model.SensorType;
import it.caoxin.smarthome.domain.service.sensortype.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sensorTypeService")
public class SensorTypeServiceImpl implements SensorTypeService {
    @Autowired
    private SensorTypeMapper sensorTypeMapper;
    @Override
    public int deleteById(Integer id) {
        return sensorTypeMapper.deleteById(id);
    }

    @Override
    public int insert(SensorType sensorType) {
        return sensorTypeMapper.insert(sensorType);
    }

    @Override
    public int insertSelective(SensorType sensorType) {
        return sensorTypeMapper.insertSelective(sensorType);
    }

    @Override
    public SensorType selectById(Integer id) {
        return sensorTypeMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(SensorType sensorType) {
        return sensorTypeMapper.updateByIdSelective(sensorType);
    }

    @Override
    public int updateById(SensorType sensorType) {
        return sensorTypeMapper.updateById(sensorType);
    }
}
