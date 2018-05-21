package it.caoxin.smarthome.app.controller.manager.deviceoperator;

import it.caoxin.smarthome.domain.model.DeviceOperator;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import it.caoxin.smarthome.domain.service.deviceoperator.DeviceOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceOperatorManagerController {
    @Autowired
    DeviceOperatorService deviceOperatorService;



    //查询所有设备操作
    @RequestMapping("/mget_deviceoperator")
    @ResponseBody
    public String getAllDeviceOperator(Integer index){
        return deviceOperatorService.getAllDeviceOperator(index);
    }

    //通过设备id查看设备操作
    @RequestMapping("/mget_deviceoperatorbydevice")
    @ResponseBody
    public String getAllDeviceOperatorByDeviceId(Integer deviceId,Integer index){
        return deviceOperatorService.getAllDeviceOperatorByDeviceId(deviceId,index);
    }

    @PostMapping("mdelete_deviceoperator")
    @ResponseBody
    public String deleteDeviceOperator(@RequestBody DeviceOperator deviceOperator){
        System.out.println("deviceOperator:"+deviceOperator.getId());
        return deviceOperatorService.mDeleteDeviceOperator(deviceOperator.getId());
    }

    @PostMapping("madd_deviceoperator")
    @ResponseBody
    public String addDeviceOperator(@RequestBody DeviceOperator deviceOperator){
        System.out.println("啊："+deviceOperator);
        return deviceOperatorService.mAddDeviceOperator(deviceOperator);
    }



}
