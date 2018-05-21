package it.caoxin.smarthome.domain.service.device;

import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface DeviceService {
    int deleteById(Integer id);

    int insert(Device device);

    int insertSelective(Device device);

    Device selectById(Integer id);

    int updateByIdSelective(Device device);

    int updateById(Device device);

//    String initDevice(Integer familyId);
    /*
    控制设备
     */
    String operatorDevice(Integer familyId,Integer userId,Integer deviceId,String operator) throws TimeoutException, ExecutionException;

    /*
    管理员添加可用设备
     */
    String getCanAddDevices(User user, Family family);

    /*
    创建设备
     */
    String createDevice(Integer familyId,Integer userId,Integer deviceId);

    /*
    删除设备
     */
    String deleteDevice(Family family,User user,Integer deviceId);

    /*
    通过家庭id查询家庭设备
     */
    String getDeviceByFamilyId(Family family);

    /*
    通过设备id查询对应的设备详情
     */
    String getDeviceById(Device device);

    /*
    更新设备
     */
    String updateDevice(Device device);

    /*
    获取所有管理的设备
     */
    String getAllDeive();

    /*
    模糊查询管理员设备
     */
    String  fuzzyGetDevice(String name);

    /*
    获取所有设备
     */
    String getAllMemberDevice(Integer index);

    /*
    模糊查询所有成员设备
     */
    String fuzzyGetAllMemberDevice(String name,Integer index);

    /*
    删除设备
     */
    String mDeleteDevice(Integer deviceId);

    /*
    管理员上传设备图片
     */
    String mAddDevice(Device device);

    String uploadDevicePhoto(MultipartFile file, HttpServletRequest request);

    /*
    获取所有设备
     */
    String getDevices();

}
