package family;

import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestFamily {
    @Autowired
    private FamilyMapper familyMapper;

    @Test
    public void testFamilyAdd(){
        Family family = new Family();
        family.setFamilyUniqueCode("test0003");
        family.setFamilyName("testName");
        family.setControlMode("testMode");
        family.setStatus("testStatus");
        familyMapper.insert(family);


        familyMapper.selectByUniqueCode(family.getFamilyUniqueCode());


    }

    @Test
    public void testFamilyDelete(){
        familyMapper.deleteById(1);
    }

    @Test
    public void testUpdate(){
        Family family = new Family();
        family.setId(2);
        family.setFamilyUniqueCode("test0002");
        family.setFamilyName("testName2");
        family.setFamilyAddress("testAddress2");
        family.setControlMode("testMode2");
        family.setStatus("testStatus2");
        familyMapper.updateById(family);
    }
    @Test
    public void testUpdateSelective(){
        Family family = new Family();
        family.setId(2);
        family.setFamilyUniqueCode("test0005");
        familyMapper.updateByIdSelective(family);

    }

    @Test
    public void testSelectById(){
        Family family = familyMapper.selectById(42);
        System.out.println("family:"+family.toString());
    }

    @Test
    public void testSelectByUniqueCode(){
        Family family = familyMapper.selectById(42);
        System.out.println("family:"+family.toString());
    }


}
