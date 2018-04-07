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
import java.util.UUID;

/**
 * @author caoxin
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //手机验证
    @Override
    public String isRegister(String phone) {
        System.out.println("phone:"+phone);
        User user = null;
        if (phone != null){
            user = userMapper.selectUserByPhone(phone);
        }

        System.out.println("user:"+user);
        if (user != null){
            //代表该账号已存在，不能注册。
            return "validFailure";
        }
        //可以注册。
        return "validSuccess";
    }

    //用户注册,注册的时候是否需要输入验证码
    @Override
    public String userRegister(User user) {

        if (user.getPhone() != null && user.getPhone().length() == 11
                && user.getPassword() != null && user.getPassword().length() > 0){
            //设置用户为成员
            user.setRole(User.MEMBER);
            userMapper.insertSelective(user);

            return "registerSuccess";//成功注册
        }
        //注册失败
        return "registerFailure";
    }

    //用户登录方式一
    @Override
    public String userLoginUsePassword(User user, HttpSession session) {
        Map valueStack = new HashMap();
        //拿到登录对象
        User loginUser = null;
        if (user.getPhone() != null && user.getPhone().length() == 11
                && user.getPassword() != null && user.getPassword().length() > 0) {
             loginUser = userMapper.selectByPhoneAndPassword(user);
        }
        if (loginUser != null){
            //将对象放入session中
            session.setAttribute("user",loginUser);
            //处理密码为空
            loginUser.setPassword("");
            //将值对象转换成json并返回
            valueStack.put("user",loginUser);
            valueStack.put("session", UUID.randomUUID().toString());
            JSONArray json = JSONArray.fromObject(valueStack);
            return json.toString();//登录成功返回User对象
        }

        return "loginFailure";//登录失败提示账号密码错误

    }

    //用户登录方式二
    @Override
    public String userLoginUseValidateCode(User user,HttpSession session,String code) {
        Map valueStack = new HashMap();
        if(user.getPhone() != null && user.getPhone().length() > 0){
            //判断该用户是否存在
            User loginUser = userMapper.selectUserByPhone(user.getPhone());

            if(loginUser != null){
                if (loginUser.getCode().equals(code)){
                    //将对象放入session中
                    session.setAttribute("user",loginUser);
                    //去除code
                    loginUser.setCode("");
                    userMapper.updateByIdSelective(loginUser);
                    //处理密码为空
                    loginUser.setPassword("");
                    //将值对象转换成json并返回
                    valueStack.put("user",loginUser);
                    valueStack.put("session", UUID.randomUUID().toString());
                    JSONArray json = JSONArray.fromObject(valueStack);
                    return json.toString();//登录成功返回User对象
                }

            }
        }
        //前端获取验证码成功时，请求登录，如果用户不存在则cann't login提示用户需要注册
        return "cann'tLogin";
    }

    //发送验证码
    @Override
    public String sendValidateCode() {
        return SendValidateCode.testCode();
    }

    //用户退出
    @Override
    public String userLogOut(HttpSession session) {
        //清除对应的session中的所有数据
        session.invalidate();
        return "logoutSuccess";
    }
    //找回密码
    @Override
    public String getBackPassword(String phone) {
        //用户输入手机后

        return getValidateCodeByPhone(phone);//没有对应手机的的用户
    }
    //用户修改密码
    @Override
    public String updatePwd(User user, String code) {
        //
        if (user.getPhone() != null && user.getPassword() != null && code != null){
            User updateUser = userMapper.selectUserByPhone(user.getPhone());
            if (updateUser.getCode().equals(code)){
                updateUser.setPassword(user.getPassword());

                userMapper.updateByIdSelective(updateUser);
                return "updatePwdSuccess";
            }
        }
        return "updatePwdFailure";
    }

    //用户信息修改
    @Override
    public String updateUserInfo(User user) {
        if (user != null && user.getId() != null){
            userMapper.updateByIdSelective(user);

            User updateUser = userMapper.selectById(user.getId());
            return JSONArray.fromObject(updateUser).toString();
        }
        return "updateUserInfoFailure";//提示无用户标识
    }
    //通过手机获取验证码
    @Override
    public String getValidateCodeByPhone(String phone) {
        System.out.println("phone:"+phone);
        //用户输入手机后
        if (phone != null && phone.length() > 0){
            User getValidateCodeUser = userMapper.selectUserByPhone(phone);
            System.out.println("user:"+getValidateCodeUser);
            if (getValidateCodeUser != null){

                String code = sendValidateCode();
                getValidateCodeUser.setCode(code);

                userMapper.updateByIdSelective(getValidateCodeUser);

                return code;
            }
        }
        return "getValidateCodeFailure";//提示没有对应的手机用户
    }


}
