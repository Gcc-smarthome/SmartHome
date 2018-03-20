package sensorData;

import it.caoxin.smarthome.domain.mapper.sensordata.SensorDataMapper;
import it.caoxin.smarthome.domain.model.SensorData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class SensorDataTest {
    @Autowired
    private SensorDataMapper sensorDataMapper;

    @Test
    public void testAdd(){
        SensorData sensorData = new SensorData();
        sensorData.setSensorId(3);
        sensorData.setDataName("test");
        sensorData.setDataValue("test");
        sensorData.setStatus("test");

        sensorDataMapper.insert(sensorData);
    }
    @Test
    public void testUpdate(){
        SensorData sensorData = new SensorData();
        sensorData.setId(1);
        sensorData.setSensorId(3);
        sensorData.setDataName("test");
        sensorData.setDataValue("test");
        sensorData.setStatus("test");

        sensorDataMapper.updateById(sensorData);

    }

    @Test
    public void testUpateSelect(){
        SensorData sensorData = new SensorData();
        sensorData.setId(1);
        sensorData.setSensorId(3);
        sensorData.setDataName("test");
        sensorData.setDataValue("test");
        sensorData.setStatus("update");

        sensorDataMapper.updateByIdSelective(sensorData);
    }
}
