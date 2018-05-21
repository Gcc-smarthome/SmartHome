package it.caoxin.smarthome.app.controller.manager.device;

import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class DeviceManagerController {
    @Autowired
    DeviceService deviceService;

    //查询管理员的设备
    @RequestMapping("/mget_device")
    @ResponseBody
    public String getAllDevice(){
        return deviceService.getAllDeive();
    }

    //模糊查询管理员的设备
    @RequestMapping("/mfuzzyget_device")
    @ResponseBody
    public String fuzzyGetDevice(String name){
        return deviceService.fuzzyGetDevice(name);
    }

    //查询所有成员设备
    @RequestMapping("/mget_alldevice")
    @ResponseBody
    public String getAllMemberDevice(Integer index){
        return deviceService.getAllMemberDevice(index);
    }

    //模糊查询所有成员设备
    @RequestMapping("/mfuzzyget_alldevice")
    @ResponseBody
    public String fuzzyGetAllMemberDevice(String name,Integer index){
        return deviceService.fuzzyGetAllMemberDevice(name,index);
    }

    @PostMapping("mdelete_device")
    @ResponseBody
    public String mDeleteDevice(@RequestBody Device device){
        return deviceService.mDeleteDevice(device.getId());
    }

    @PostMapping("madd_device")
    @ResponseBody
    public String mAddDevice(@RequestBody Device device){
        System.out.println("啊："+device);
        return deviceService.mAddDevice(device);
    }

    @RequestMapping(value = "mupload_device",method = RequestMethod.POST)
    @ResponseBody
    public String muploadDevicePhoto(MultipartFile file, HttpServletRequest request){
        return deviceService.uploadDevicePhoto(file,request);
    }

    @RequestMapping("/mget_alldevices")
    @ResponseBody
    public String getDevices(){
        return deviceService.getDevices();
    }


}
