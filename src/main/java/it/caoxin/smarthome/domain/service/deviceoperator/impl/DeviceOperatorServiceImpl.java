package it.caoxin.smarthome.domain.service.deviceoperator.impl;

import it.caoxin.smarthome.domain.mapper.deviceoperator.DeviceOperatorMapper;
import it.caoxin.smarthome.domain.model.DeviceOperator;
import it.caoxin.smarthome.domain.service.deviceoperator.DeviceOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceOperatorService")
public class DeviceOperatorServiceImpl implements DeviceOperatorService{
    @Autowired
    private DeviceOperatorMapper deviceOperatorMapper;
    @Override
    public int deleteById(Integer id) {
        return deviceOperatorMapper.deleteById(id);
    }

    @Override
    public int insert(DeviceOperator deviceOperator) {
        return deviceOperatorMapper.insert(deviceOperator);
    }

    @Override
    public int insertSelective(DeviceOperator deviceOperator) {
        return deviceOperatorMapper.insertSelective(deviceOperator);
    }

    @Override
    public DeviceOperator selectById(Integer id) {
        return deviceOperatorMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(DeviceOperator deviceOperator) {
        return deviceOperatorMapper.updateByIdSelective(deviceOperator);
    }

    @Override
    public int updateById(DeviceOperator deviceOperator) {
        return deviceOperatorMapper.updateById(deviceOperator);
    }
}
