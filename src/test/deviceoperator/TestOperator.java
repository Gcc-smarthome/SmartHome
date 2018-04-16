package deviceoperator;

import it.caoxin.smarthome.domain.mapper.deviceoperator.DeviceOperatorMapper;
import it.caoxin.smarthome.domain.model.DeviceOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestOperator {
    @Autowired
    private DeviceOperatorMapper deviceOperatorMapper;

    @Test
    public void testAdd(){
        DeviceOperator deviceOperator = new DeviceOperator();
        deviceOperator.setOperatorName("test");
        deviceOperator.setOperatorCode("test_test");
        deviceOperator.setStatus("test");
        deviceOperator.setDeviceId(3);

        deviceOperatorMapper.insertSelective(deviceOperator);
    }

    @Test
    public void testUpdate(){
        DeviceOperator deviceOperator = new DeviceOperator();
        deviceOperator.setId(1);
        deviceOperator.setOperatorName("test3");
        deviceOperator.setOperatorCode("test_test3");
        deviceOperator.setStatus("test3");
        deviceOperator.setDeviceId(3);

        deviceOperatorMapper.updateById(deviceOperator);

    }

    @Test
    public void testUpdateSelect(){
        DeviceOperator deviceOperator = new DeviceOperator();
        deviceOperator.setId(1);
        deviceOperator.setOperatorName("test4");

        deviceOperatorMapper.updateByIdSelective(deviceOperator);
    }

    @Test
    public void testSelect(){
        DeviceOperator deviceOperator = deviceOperatorMapper.selectById(1);
        System.out.println("deviceOperator:"+deviceOperator);
    }
}
