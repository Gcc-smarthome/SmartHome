package it.caoxin.smarthome.domain.mapper.device;


import it.caoxin.smarthome.domain.model.Device;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;

/**
 * @author Administrator
 */
public interface DeviceMapper {
    int deleteById(Integer id);

    int insert(Device device);

    int insertSelective(Device device);

    Device selectById(Integer id);

    int updateByIdSelective(Device device);

    int updateById(Device device);

    List<Device> selectDeviceByFamilyId(Integer familyId);
}