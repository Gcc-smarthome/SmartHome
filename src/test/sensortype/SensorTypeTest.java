package sensortype;

import it.caoxin.smarthome.domain.mapper.sensortype.SensorTypeMapper;
import it.caoxin.smarthome.domain.model.SensorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class SensorTypeTest {

    @Autowired
    private SensorTypeMapper sensorTypeMapper;

    @Test
    public void testAdd(){
        SensorType sensorType = new SensorType();
        sensorType.setName("test3");
        sensorType.setStatus("test");

        sensorTypeMapper.insert(sensorType);
    }
    @Test
    public void update(){
        SensorType sensorType = new SensorType();
        sensorType.setId(4);
        sensorType.setName("test3");
        sensorType.setStatus("update");

        sensorTypeMapper.updateById(sensorType);
    }
    @Test
    public void updateSelect(){
        SensorType sensorType = new SensorType();
        sensorType.setId(4);
        sensorType.setName("test3");
        sensorType.setStatus("update1");

        sensorTypeMapper.updateByIdSelective(sensorType);
    }

    @Test
    public void select(){
        SensorType sensorType = sensorTypeMapper.selectById(1);
        System.out.println("sensorType:"+sensorType);
    }

    @Test
    public void testGetAll(){
        List<SensorType> allSensorType =
                sensorTypeMapper.getAllSensorType();

        System.out.println("啊："+allSensorType);
    }


    @Test
    public void testFuzzyGet(){
        List<SensorType> allList = sensorTypeMapper.getAllSensorTypeLikeName("光");
        System.out.println("啊："+allList);
    }
}
