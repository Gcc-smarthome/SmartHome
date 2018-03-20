package testlog;

import it.caoxin.smarthome.domain.mapper.log.LogMapper;
import it.caoxin.smarthome.domain.model.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestLog {
    @Autowired
    private LogMapper logMapper;

    @Test
    public void testAdd(){
        Log log = new Log();
        log.setFamilyId(4);
        log.setType("testLog");
        log.setStatus("testStatus");
        logMapper.insert(log);
    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testSelect(){
        Log log = logMapper.selectById(1);

        System.out.println("log:"+log);
    }

    @Test
    public void testUpdate(){
        Log log = new Log();
        log.setId(1);
        log.setFamilyId(4);
        log.setType("testUpdateLog");
        log.setStatus("testUpdateStatus");
        logMapper.updateById(log);
    }
    @Test
    public void testUpdateSelect(){
        Log log = new Log();
        log.setId(1);
        log.setType("testUpdateSelective");
        logMapper.updateByIdSelective(log);
    }
}
