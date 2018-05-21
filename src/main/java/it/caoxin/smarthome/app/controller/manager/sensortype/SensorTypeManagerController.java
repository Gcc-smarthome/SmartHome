package it.caoxin.smarthome.app.controller.manager.sensortype;

import it.caoxin.smarthome.domain.model.SensorType;
import it.caoxin.smarthome.domain.service.sensor.SensorService;
import it.caoxin.smarthome.domain.service.sensortype.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SensorTypeManagerController {
    @Autowired
    private SensorTypeService sensorTypeService;

    @RequestMapping("/mget_sensortype")
    @ResponseBody
    public String getAllSensorType(){
        return  sensorTypeService.getAllSensorType();
    }

    @RequestMapping("/mfuzzyget_sensortype")
    @ResponseBody
    public String fuzzyGetSensorType(String name){
        return  sensorTypeService.fuzzyGetByName(name);
    }

}
