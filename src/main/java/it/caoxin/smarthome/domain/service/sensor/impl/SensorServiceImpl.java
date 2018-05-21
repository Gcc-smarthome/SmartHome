package it.caoxin.smarthome.domain.service.sensor.impl;

import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.domain.common.PageBean;
import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.model.DeviceOperator;
import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.model.SensorType;
import it.caoxin.smarthome.domain.service.sensor.SensorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sensorService")
public class SensorServiceImpl implements SensorService {
    @Autowired
    private SensorMapper sensorMapper;
    @Override
    public int deleteById(Integer id) {
        return sensorMapper.deleteById(id);
    }

    @Override
    public int insert(Sensor sensor) {
        return sensorMapper.insert(sensor);
    }

    @Override
    public int insertSelective(Sensor sensor) {
        return sensorMapper.insertSelective(sensor);
    }

    @Override
    public Sensor selectById(Integer id) {
        return sensorMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(Sensor sensor) {
        return sensorMapper.updateByIdSelective(sensor);
    }

    @Override
    public int updateById(Sensor sensor) {
        return sensorMapper.updateById(sensor);
    }

    @Override
    public String getAllSensor(Integer index) {
        PageBean<Sensor> pageBean = new PageBean<>();
        pageBean.setTotal(sensorMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<Sensor> allSensor = sensorMapper.getAllSensor();
        pageBean.setPage(index);
        pageBean.setBeanList(allSensor);

        JSONObject bean = JSONObject.fromObject(pageBean);
        return bean.toString();
    }

    @Override
    public String getSensorByDeviceId(Integer deviceId,Integer index) {
        if (deviceId != null) {
            PageBean<Sensor> pageBean = new PageBean<>();
            pageBean.setTotal(sensorMapper.selectSensorByDeviceId(deviceId).size());

            if (index == null) {
                index = 1;
            }
            PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
            List<Sensor> sensors = sensorMapper.selectSensorByDeviceId(deviceId);
            pageBean.setPage(index);
            pageBean.setBeanList(sensors);

            JSONObject bean = JSONObject.fromObject(pageBean);
            return bean.toString();
        }
        return getAllSensor(index);
    }

    @Override
    public String mDeleteSensor(Integer sensorId) {
        if (sensorId != null) {
            System.out.println("sensorId:"+sensorId);
            sensorMapper.deleteById(sensorId);
            return "delete Success";
        }
        return "id can not be null";
    }

    @Override
    public String mAddSensor(Sensor sensor) {
        if (sensor.getDeviceId() != null ){
            sensorMapper.insertSelective(sensor);
            return "add sensor success";
        }
        return "add sensor failure";
    }

}
