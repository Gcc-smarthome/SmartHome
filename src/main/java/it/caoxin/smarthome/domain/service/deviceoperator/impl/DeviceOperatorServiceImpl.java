package it.caoxin.smarthome.domain.service.deviceoperator.impl;

import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.domain.common.PageBean;
import it.caoxin.smarthome.domain.mapper.deviceoperator.DeviceOperatorMapper;
import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.DeviceOperator;
import it.caoxin.smarthome.domain.model.UserFamily;
import it.caoxin.smarthome.domain.service.deviceoperator.DeviceOperatorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String getAllDeviceOperator(Integer index) {
        PageBean<DeviceOperator> pageBean = new PageBean<>();
        pageBean.setTotal(deviceOperatorMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<DeviceOperator> allDevice = deviceOperatorMapper.getAllDevice();
        pageBean.setPage(index);
        pageBean.setBeanList(allDevice);

        JSONObject bean = JSONObject.fromObject(pageBean);
        return bean.toString();
    }

    @Override
    public String getAllDeviceOperatorByDeviceId(Integer deviceId,Integer index) {
        if (deviceId != null){
            PageBean<DeviceOperator> pageBean = new PageBean<>();
            pageBean.setTotal(deviceOperatorMapper.selectOperatorByDeviceId(deviceId).size());

            if (index == null){
                index = 1;
            }
            PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
            List<DeviceOperator> allDevice = deviceOperatorMapper.selectOperatorByDeviceId(deviceId);
            pageBean.setPage(index);
            pageBean.setBeanList(allDevice);

            JSONObject bean = JSONObject.fromObject(pageBean);
            return bean.toString();
        }
        return getAllDeviceOperator(index);
    }

    @Override
    public String mDeleteDeviceOperator(Integer deviceOperatorId) {
        if (deviceOperatorId != null) {
            System.out.println("deviceOperatorId:"+deviceOperatorId);
            deviceOperatorMapper.deleteById(deviceOperatorId);
            return "delete Success";
        }
        return "id can not be null";
    }

    @Override
    public String mAddDeviceOperator(DeviceOperator deviceOperator) {
        if (deviceOperator != null && deviceOperator.getDeviceId() != null){
            insertSelective(deviceOperator);
            return "add Success";
        }
        return "add Failure";
    }
}
