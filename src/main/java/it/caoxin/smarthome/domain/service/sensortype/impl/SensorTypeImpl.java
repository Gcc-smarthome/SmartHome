package it.caoxin.smarthome.domain.service.sensortype.impl;

import it.caoxin.smarthome.domain.mapper.sensortype.SensorTypeMapper;
import it.caoxin.smarthome.domain.model.SensorType;
import it.caoxin.smarthome.domain.service.sensortype.SensorTypeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sensorTypeService")
public class SensorTypeImpl implements SensorTypeService {
    @Autowired
    private SensorTypeMapper sensorTypeMapper;

    @Override
    public String getAllSensorType() {
        List<SensorType> allSensorType = sensorTypeMapper.getAllSensorType();

        return JSONArray.fromObject(allSensorType).toString();
    }

    @Override
    public String fuzzyGetByName(String name) {
        List<SensorType> allSensorType = sensorTypeMapper.getAllSensorTypeLikeName(name);

        return JSONArray.fromObject(allSensorType).toString();
    }
}
