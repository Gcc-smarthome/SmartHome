package it.caoxin.smarthome.app.controller.device;


import it.caoxin.smarthome.domain.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceContoller {
    @Autowired
    private DeviceService deviceService;
    /**
     *@Author:Caoxin
     *@Description
     *@Date:12:46 2018/2/2
     *@param[id]:family_id
     *@returnjava.lang.String
     */
    @RequestMapping("/devices/{id}")
    @ResponseBody
    public String initDevice(@PathVariable Integer id){
        System.out.println("id:"+id);
        return deviceService.initDevice(id);
    }


    /**
     *@Author:Caoxin
     *@Description
     *@Date:21:15 2018/2/2
     *@param[familyId, operator]
     *@returnjava.lang.String
     */
    @RequestMapping("/device/{familyId}/{operator}")
    @ResponseBody
    public String operatorDevice(@PathVariable Integer familyId,
                                 @PathVariable String operator){
        System.out.println("familyId:"+familyId);
        System.out.println("operator:"+operator);

        return deviceService.operatorDevice(familyId,operator);

    }

    @RequestMapping("/devices")
    @ResponseBody
    public String getCanAddDevices(){
        return deviceService.getCanAddDevices();
    }

    @RequestMapping("/device/{familyId}/{deviceName}")
    @ResponseBody
    public String creatDevice(@PathVariable Integer familyId,
                                 @PathVariable String deviceName){
        System.out.println("familyId:"+familyId);
        System.out.println("operator:"+deviceName);

        return deviceService.createDevice(familyId,deviceName);

    }

}
