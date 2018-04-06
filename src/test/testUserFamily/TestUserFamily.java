package testUserFamily;

import it.caoxin.smarthome.domain.model.UserFamily;
import it.caoxin.smarthome.domain.mapper.userfamily.UserFamilyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestUserFamily {
    @Autowired
    private UserFamilyMapper userFamilyMapper;

    @Test
    public void testAdd(){
        UserFamily userFamily = new UserFamily();
        userFamily.setUserId(1);
        userFamily.setFamilyId(1);
        userFamily.setStatus("正常");
        userFamily.setFamilyRole("123");

        userFamilyMapper.insert(userFamily);
    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testSelect(){

        UserFamily userFamily = userFamilyMapper.selectById(1);
        System.out.println("log:"+userFamily);
    }

    @Test
    public void testUpdate(){
        UserFamily userFamily = new UserFamily();
        userFamily.setId(1);
        userFamily.setStatus("正常了");
        userFamily.setFamilyRole("123");
        userFamilyMapper.updateSelect(userFamily);
    }
}
