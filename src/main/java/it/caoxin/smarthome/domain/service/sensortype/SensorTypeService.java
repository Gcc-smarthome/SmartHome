package it.caoxin.smarthome.domain.service.sensortype;

import it.caoxin.smarthome.domain.model.SensorType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SensorTypeService {

    /*
管理员获得所有传感器类型
 */
    String getAllSensorType();

    String fuzzyGetByName(String name);
}
