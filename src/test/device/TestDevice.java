package device;


import it.caoxin.SmartHomeServer.client.SmartHomeRpcProxy;
import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.model.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
        Device device = deviceMapper.selectById(1);
        System.out.println(device);
    }

    @Test
    public void testRpc() throws ClassNotFoundException {
        SmartHomeRpcProxy rpcProxy = new SmartHomeRpcProxy("localhost:8000");
        Class<?> aClass = Class.forName("it.caoxin.smarthome.domain.service.RpcService.rpcserviceinterface.HelloService");

        String name = aClass.getDeclaringClass().getName();
        System.out.println("name:"+name);


    }

    @Test
    public void testgetAll(){
        List<Device> managerDevice =
                deviceMapper.getManagerDevice();
        System.out.println("啊："+managerDevice);
    }

    @Test
    public void testFuzzyGetAll(){
        List<Device> managerDevice =
                deviceMapper.fuzzyGetManagerDevice("c");
        System.out.println("啊："+managerDevice);
    }

}
