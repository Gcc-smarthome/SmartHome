package it.caoxin.smarthome.domain.mapper.deviceoperator;


import it.caoxin.smarthome.domain.model.DeviceOperator;

import java.util.List;

public interface DeviceOperatorMapper {
    int deleteById(Integer id);

    int insert(DeviceOperator deviceOperator);

    int insertSelective(DeviceOperator deviceOperator);

    DeviceOperator selectById(Integer id);

    int updateByIdSelective(DeviceOperator deviceOperator);

    int updateById(DeviceOperator deviceOperator);

    List<DeviceOperator> selectOperatorByDeviceId(Integer deviceId);

    List<DeviceOperator> getAllDevice();

    Integer getCount();
}