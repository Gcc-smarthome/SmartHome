package it.caoxin.smarthome.domain.service.user.impl;



import it.caoxin.smarthome.domain.common.SendValidateCode;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.user.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoxin
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FamilyMapper familyMapper;
    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }


    @Override
    public int updateByIdSelective(User user) {
        return userMapper.updateByIdSelective(user);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }



    @Override
    public String userLogin(User user, HttpSession session) {
        //返回值对象
        Map valueStack = new HashMap<String,Object>();

        User validateUser = userMapper.selectByPhoneAndPassword(user);
        if (validateUser != null){
            JSONArray jsonUser = JSONArray.fromObject(validateUser);
            //查询对应的家庭信息并添加到Map中
            Integer familyId = validateUser.getFamilyId();

            //如果有家庭
            if(familyId != null){
                Family family = familyMapper.selectById(familyId);
                JSONArray jsonFamily = JSONArray.fromObject(family);

                valueStack.put("family",family);
                session.setAttribute("family",jsonFamily);
            }
            //将查询到的用户信息并添加到Map中
            valueStack.put("user",validateUser);

            //将对应用户信息传到session中
            session.setAttribute("user",jsonUser);

            //将值对象转换成json并返回
            JSONArray jsonMap = JSONArray.fromObject(valueStack);
            return jsonMap.toString();

        }else {
            return "loginFail";
        }
    }

    @Override
    public String userLogOut(HttpSession session) {
        //清除对应的session中的所有数据
        session.invalidate();
        return "logoutSuccess";
    }

    @Override
    public String sendValidateCode(String phone) {
        if (userMapper.selectUserByFamilyPhone(phone) != null){
            return"this phone has registered";
        }
        return new SendValidateCode().testCode();
    }

    @Override
    public String userRegister(User user) {
        try {
            userMapper.insertSelective(user);
        }catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    @Override
    public String getUserInfo(Integer userId) {
        User user = null;
        if (userId != null){
            user = userMapper.selectById(userId);
        }else{
            return "没有用户主标识";
        }
        return JSONArray.fromObject(user).toString();
    }


}
