package it.caoxin.smarthome.domain.service.sensordata.impl;


import it.caoxin.smarthome.domain.mapper.sensordata.SensorDataMapper;
import it.caoxin.smarthome.domain.model.SensorData;
import it.caoxin.smarthome.domain.service.sensordata.SensorDataService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sensorDataService")
public class SensorDataServiceImpl implements SensorDataService {
    @Autowired
    private SensorDataMapper sensorDataMapper;
    @Override
    public int deleteById(Integer id) {
        return sensorDataMapper.deleteById(id);
    }

    @Override
    public int insert(SensorData sensorData) {
        return sensorDataMapper.insert(sensorData);
    }

    @Override
    public int insertSelective(SensorData sensorData) {
        return sensorDataMapper.insertSelective(sensorData);
    }

    @Override
    public SensorData selectById(Integer id) {
        return sensorDataMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(SensorData sensorData) {
        return sensorDataMapper.updateByIdSelective(sensorData);
    }

    @Override
    public int updateById(SensorData sensorData) {
        return sensorDataMapper.updateById(sensorData);
    }

    @Override
    public String getSensorsData(Integer sensorId) {
        if (sensorId != null ){
            List<SensorData> sensorData = sensorDataMapper.getLatelyDataBySensorId(sensorId);
            Collections.reverse(sensorData);
            return JSONArray.fromObject(sensorData).toString();
        }
        return "no sensor id";
    }
}
