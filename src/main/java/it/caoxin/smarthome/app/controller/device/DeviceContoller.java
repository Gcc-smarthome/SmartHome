package it.caoxin.smarthome.app.controller.device;


import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceContoller {
    @Autowired
    private DeviceService deviceService;

    //添加设备
    @RequestMapping(value = "add_device",method = RequestMethod.POST)
    @ResponseBody
    public String creatDevice( Integer familyId, Integer userId, String deviceName){
        System.out.println("familyId:"+familyId);
        System.out.println("deviceName:"+deviceName);

        return deviceService.createDevice(familyId,userId,deviceName);

    }

    //删除设备
    @RequestMapping(value = "del_device",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDevice(Integer deviceId){
        return deviceService.deleteDevice(deviceId);

    }

    //控制设备
    @RequestMapping(value = "control_device",method = RequestMethod.POST)
    @ResponseBody
    public String operatorDevice(Integer familyId,
                                  String operator,
                                  Integer deviceId ){
        System.out.println("familyId:"+familyId);
        System.out.println("operator:"+operator);

        return deviceService.operatorDevice(familyId,deviceId,operator);

    }

    //获取可以添加的设备 管理员才可以添加
    @RequestMapping(value = "get_device",method = RequestMethod.GET)
    @ResponseBody
    public String getDevices(Integer familyId,
                                 Integer userId ){

        User user =new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        return deviceService.getCanAddDevices(user,family);

    }

}
