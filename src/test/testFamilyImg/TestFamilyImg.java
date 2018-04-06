package testFamilyImg;

import it.caoxin.smarthome.domain.mapper.familyimg.FamilyImgMapper;
import it.caoxin.smarthome.domain.model.FamilyImg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestFamilyImg {
    @Autowired
    private FamilyImgMapper familyImgMapper;

    @Test
    public void testAdd(){
        FamilyImg img = new FamilyImg();
        img.setFamilyId(1);
        img.setImgUrl("123");
        img.setStatus("正常");
        familyImgMapper.insert(img);
    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testSelect(){

        FamilyImg img = familyImgMapper.selectById(1);
        System.out.println("log:"+img);
    }

    @Test
    public void testUpdate(){
        FamilyImg img = new FamilyImg();
        img.setId(1);
        img.setImgUrl("456");
        familyImgMapper.updateSelect(img);
    }
    @Test
    public void testUpdateSelect(){

    }

}
