package it.caoxin.smarthome.app.controller.sensordata;

import it.caoxin.smarthome.domain.model.Message;
import it.caoxin.smarthome.domain.model.SensorData;
import it.caoxin.smarthome.domain.service.sensordata.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Date;


@Controller
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;


    @MessageMapping("/add_sensordata")
    @SendToUser
    public void addSensorData(SensorData message){

       message.setCollectionTime(new Date());
       message.setStatus(SensorData.STATUS_NORMAL);

       messagingTemplate.convertAndSendToUser("1", "/add_Data/sensordata",new Message(message));

       sensorDataService.insertSelective(message);
    }

    @PostMapping("/test")
    @ResponseBody
    public String add(@RequestBody SensorData message){

        message.setCollectionTime(new Date());
        message.setStatus(SensorData.STATUS_NORMAL);

        messagingTemplate.convertAndSendToUser("1", "/add_Data/sensordata",new Message(message));
        return "";
//        sensorDataService.insertSelective(message);
    }

    @RequestMapping(value = "/get_sensorsdata",method = RequestMethod.GET)
    @ResponseBody
    public String getSensorsData(Integer sensorsId){
        System.out.println("sensorsId:"+sensorsId);
        return sensorDataService.getSensorsData(sensorsId);
    }
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SensorDataController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

}
