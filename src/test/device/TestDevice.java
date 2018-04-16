package device;


import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.model.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestDevice {

    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    public void test2Add(){
        Device device = new Device();
        device.setFamilyId(1);
        device.setName("test3");
        device.setStatus("test3");
        device.setControlMode("test3");

        deviceMapper.insert(device);
    }

    @Test
    public void testUpdate(){
        Device device = new Device();
        device.setId(11);
        device.setName("testupdate");
        device.setStatus("testupdate");
        device.setControlMode("testupdate");

        deviceMapper.updateById(device);
    }

    @Test
    public void testUpdateSelect(){
        Device device = new Device();
        device.setId(11);
        device.setName("testupdate111");
        deviceMapper.updateByIdSelective(device);

    }

    @Test
    public void testSelect(){
        Device device = deviceMapper.selectById(18);
        System.out.println(device);
    }


}
