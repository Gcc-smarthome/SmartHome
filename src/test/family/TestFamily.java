package family;

import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestFamily {
    @Autowired
    private FamilyMapper familyMapper;

    @Test
    public void testFamilyAdd(){
        Family family = new Family();
        family.setFamilyUniqueCode("test0004");
        family.setFamilyName("曹欣");
        family.setControlMode("testMode");
        family.setStatus("testStatus");
        family.setFamilyAddress("1213132");
        familyMapper.insertSelective(family);

        System.out.println("id"+family.getId()+",,,,,,,,,,,,,,,,,,,,,");


      // familyMapper.selectByUniqueCode(family.getFamilyUniqueCode());


    }

    @Test
    public void testFamilyDelete(){
        familyMapper.deleteById(1);
    }

    @Test
    public void testUpdate(){
        Family family = new Family();
        family.setId(4);
        family.setFamilyUniqueCode("曹欣");
        family.setFamilyName("testName2");
        family.setFamilyAddress("testAddress2");
        family.setControlMode("testMode2");
        family.setStatus("testStatus2");
        familyMapper.updateById(family);
    }
    @Test
    public void testUpdateSelective(){
        Family family = new Family();
        family.setId(4);
        family.setFamilyUniqueCode("test0005");
        familyMapper.updateByIdSelective(family);

    }

    @Test
    public void testSelectById(){
        Family family = familyMapper.selectById(1);
        System.out.println("family:"+family.toString());
    }

    @Test
    public void testSelectByUniqueCode(){
        Family family = familyMapper.selectByUniqueCodeForUpdate("test0003");
        System.out.println("family:"+family.toString());
    }

    @Test
    public void testGetAllFamily(){
        List<Family> allFamily =
                familyMapper.getAllFamily();
        System.out.println("啊："+allFamily);
    }

    @Test
    public void testFuzzyGet(){
        List<Family> allFamily =
                familyMapper.getAllFamilyLikeName("曹");
        System.out.println("啊："+allFamily);
    }

}
