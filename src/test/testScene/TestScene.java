package testScene;

import it.caoxin.smarthome.domain.mapper.log.LogMapper;
import it.caoxin.smarthome.domain.mapper.scene.SceneMapper;
import it.caoxin.smarthome.domain.model.Log;
import it.caoxin.smarthome.domain.model.Scene;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestScene {
    @Autowired
    private SceneMapper sceneMapper;

    @Test
    public void testAdd(){
        Scene scene = new Scene();
        scene.setFamilyId(1);
        scene.setDeviceId(2);
        scene.setUserId(8);

        sceneMapper.insertSelective(scene);
    }

    @Test
    public void testDelete(){
        sceneMapper.deleteSceneById(1);
    }

    @Test
    public void testSelect(){
        Scene scene = new Scene();

        Scene scene1 = sceneMapper.selectByUserIdAndFamilyIdAndDeviceId(1, 2, 8);
        System.out.println("scene1"+scene1);
    }

    @Test
    public void testUpdate(){
//        Log log = new Log();
//        log.setId(1);
//        log.setFamilyId(4);
//        log.setType("testUpdateLog");
//        log.setStatus("testUpdateStatus");
//        logMapper.updateById(log);
    }
    @Test
    public void testUpdateSelect(){
//        Log log = new Log();
//        log.setId(1);
//        log.setType("testUpdateSelective");
//        logMapper.updateByIdSelective(log);
    }
}
