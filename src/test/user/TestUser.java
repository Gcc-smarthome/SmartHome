package user;

import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-dao.xml","classpath:META-INF/spring/spring-service.xml"})
public class TestUser {

    private  Logger logger = Logger.getLogger(TestUser.class);
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setPhone("test");
        user.setStatus("正常");


        userMapper.insert(user);
    }

    @Test
    public void testSelect(){
        User user = userMapper.selectById(1);
        System.out.println("user:"+user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1);
        user.setUsername("update");
        user.setPassword("update");
        user.setPhone("update");
        user.setStatus("正常");



        userMapper.updateById(user);
    }

    @Test
    public void testUpdateSelect(){
        User user = new User();
        user.setId(1);
        user.setUsername("update2");
        user.setPassword("update2");
        user.setPhone("update2");


        userMapper.updateByIdSelective(user);
    }

    @Test
    public void testFindUserByUserNameAndId(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        User user1 = userMapper.selectByUsernameAndPassword(user);
        System.out.println("user："+user1 == null ? true:false);
    }

    @Test
    public void testSelectUserByFamilyId(){

//        System.out.println("users:"+users);
//        logger.info("I will kill you !!!!");
//        logger.error("I deading");
    }

    @Test
    public void testStringToJson(){
        String str = "{'id':'1','ip':'123.123.123.123'}";
        JSONObject jsonObject = JSONObject.fromObject(str);
        String id = (String)jsonObject.get("id");
        String ip  = (String) jsonObject.get("ip");

        System.out.println("id:"+id);
        System.out.println("ip:"+ip);
    }

    @Test
    public void testJsonToList(){
        String str = "{'1': 'light','2': 'kettle','3': 'door','4': 'curtain'}";
        ArrayList<String> deviceList = new ArrayList<String>();
        JSONObject jsonObject = JSONObject.fromObject(str);

        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){
            String next = (String) keys.next();
            String o = (String) jsonObject.get(next);

            System.out.println(next+":"+o);
        }
//        int size = jsonObject.size();
//        System.out.println("size:"+size);
//
//        System.out.println("jsonObject:"+jsonObject.);
//        for(int i = 1; i < jsonObject.size(); i++){
//            String device =  jsonObject.get(i).toString();
//            System.out.println("device:"+device);
//            deviceList.add(device);
//        }
//        System.out.println("list:"+deviceList);
            
    }

    @Test
    public void testSelectIdByPhone(){
//        User user = userMapper.selectUserByFamilyPhone("13002090126");
//        System.out.println("user:"+user);
    }

    @Test
    public void testSelectIdByPhoneAndPassword(){
//       userMapper.selectByPhoneAndPassword()
//        System.out.println("user:"+user);
    }

    @Test
    public void testSelectByPhone(){
        User user = userMapper.selectUserByPhone("1");
        System.out.println("user"+user);
    }
}
