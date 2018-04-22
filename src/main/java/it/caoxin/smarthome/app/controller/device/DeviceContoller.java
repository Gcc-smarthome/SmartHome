package it.caoxin.smarthome.app.controller.device;


import it.caoxin.smarthome.domain.model.Device;
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
    public String creatDevice( Integer familyId, Integer userId, Integer deviceId){
        System.out.println("familyId:"+familyId);
        System.out.println("deviceId:"+deviceId);

        return deviceService.createDevice(familyId,userId,deviceId);

    }

    //删除设备
    @RequestMapping(value = "del_device",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDevice(Integer familyId,Integer userId,Integer deviceId){

        User user =new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        return deviceService.deleteDevice(family,user,deviceId);

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

    //同过家庭id获取这个家庭的设备
    @RequestMapping(value = "get_family_device",method = RequestMethod.GET)
    @ResponseBody
    public String getFamilyDevices(Integer familyId){

        Family family = new Family();
        family.setId(familyId);

        return deviceService.getDeviceByFamilyId(family);

    }

    //查看设备详情
    @RequestMapping(value = "get_device/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String getDeviceDetail(@PathVariable Integer id){
        System.out.println("deviceId"+id);
        Device device = new Device();
        device.setId(id);
        return deviceService.getDeviceById(device);

    }

    //更新设备设备
    @RequestMapping(value = "up_device",method = RequestMethod.POST)
    @ResponseBody
    public String updateDevice(Device device){
        System.out.println("device"+device);
        return deviceService.updateDevice(device);

    }

}
