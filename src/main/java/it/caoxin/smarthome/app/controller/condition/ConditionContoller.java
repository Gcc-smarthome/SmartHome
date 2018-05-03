package it.caoxin.smarthome.app.controller.condition;


import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.condition.ConditionService;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Controller
public class ConditionContoller {
    @Autowired
    private ConditionService conditionService;

    //添加设备
    @RequestMapping(value = "add_condition",method = RequestMethod.POST)
    @ResponseBody
    public String creatCondition(Condition condition){
        System.out.println("Condition"+condition);

        return conditionService.addCondition(condition);

    }



}
