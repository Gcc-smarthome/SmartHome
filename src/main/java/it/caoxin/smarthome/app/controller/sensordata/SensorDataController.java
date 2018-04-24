package it.caoxin.smarthome.app.controller.sensordata;

import it.caoxin.smarthome.domain.model.Message;
import it.caoxin.smarthome.domain.model.SensorData;
import it.caoxin.smarthome.domain.service.sensordata.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;


import java.util.Date;


@Controller
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;


    @MessageMapping("/add_sensordata")
    @SendToUser("/add_Data/sensordata")
    public void addSensorData(SensorData message){

       message.setCollectionTime(new Date());
       message.setStatus(SensorData.STATUS_NORMAL);

       messagingTemplate.convertAndSendToUser(message.getSensorId()+"", "/add_Data/sensordata",new Message(new SensorData()));

       sensorDataService.insertSelective(message);
    }


    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SensorDataController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

}
