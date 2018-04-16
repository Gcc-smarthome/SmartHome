package sensor;

import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.model.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestSensor {
    @Autowired
    private SensorMapper sensorMapper;

    @Test
    public void testAdd(){
        Sensor sensor = new Sensor();
        sensor.setDeviceId(3);
        sensor.setName("test");
        sensor.setSensorTypeId(3);
        sensor.setStatus("test");
       sensorMapper.insert(sensor);
    }

    @Test
    public void testUpdate(){
        Sensor sensor = new Sensor();
        sensor.setId(5);
        sensor.setDeviceId(3);
        sensor.setName("test");
        sensor.setSensorTypeId(3);
        sensor.setStatus("update");

        sensorMapper.updateById(sensor);
    }
    @Test
    public void testUpateSelect(){
        Sensor sensor = new Sensor();
        sensor.setId(5);
        sensor.setStatus("update2");
        sensorMapper.updateByIdSelective(sensor);

    }

    @Test
    public void testSelect(){
        Sensor sensor = sensorMapper.selectById(1);
        System.out.println("sensor:"+sensor);
    }


}
