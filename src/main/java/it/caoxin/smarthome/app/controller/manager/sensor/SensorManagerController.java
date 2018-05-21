package it.caoxin.smarthome.app.controller.manager.sensor;

import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import it.caoxin.smarthome.domain.service.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SensorManagerController {
    @Autowired
    private SensorService sensorService;

    @RequestMapping("/mget_sensor")
    @ResponseBody
    public String getAllSensor(Integer index){
        return sensorService.getAllSensor(index);
    }

    @RequestMapping("/mget_sensorbydeviceid")
    @ResponseBody
    public String getAllSensorByDeviceId(Integer deviceId,Integer index){

        return sensorService.getSensorByDeviceId(deviceId, index);
    }


    @PostMapping("/mdelete_sensor")
    @ResponseBody
    public String deleteUser(@RequestBody Sensor sensor){
        System.out.println("user:"+sensor.getId());
      return sensorService.mDeleteSensor(sensor.getId());
    }

    @PostMapping("/madd_sensor")
    @ResponseBody
    public String addSensor(@RequestBody Sensor sensor){
        System.out.println("啊:"+sensor);
        return sensorService.mAddSensor(sensor);
    }

}
