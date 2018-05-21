package testMybatisPageHelper;

import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TEst {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testPage(){
//        int pageNum = 1;//页码，从1开始
//        int pageSize = 5;//每页记录数
//        PageHelper.startPage(pageNum, pageSize);//指定开始分页

        List<User> allUser = userMapper.getAllUserLikeName("小");
        for (User user: allUser
             ) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void selectManager(){
        List<User> allManager = userMapper.getAllManager();
        for (User user: allManager
                ) {
            System.out.println("啊："+user.getUsername());
        }
    }

    @Test
    public void selectByName(){
        List<User> allManager = userMapper.getAllUserLikeName("曹");
        for (User user: allManager
                ) {
            System.out.println("啊："+user.getUsername());
        }
    }




}
