package it.caoxin.smarthome.domain.service.device;

import it.caoxin.smarthome.domain.model.Device;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeviceService {
    int deleteById(Integer id);

    int insert(Device device);

    int insertSelective(Device device);

    Device selectById(Integer id);

    int updateByIdSelective(Device device);

    int updateById(Device device);

    String initDevice(Integer familyId);

    String operatorDevice(Integer familyId,String operator);

    String getCanAddDevices();

    String createDevice(Integer familyId,String deviceName);
}
