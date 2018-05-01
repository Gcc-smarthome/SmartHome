package it.caoxin.smarthome.app.controller.user;

import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //判断是否能注册
    @RequestMapping(value = "/isRegister",method = RequestMethod.POST)
    @ResponseBody
    public String isRegister(String phone){
        System.out.println("---------------------------phone:"+phone);
        System.out.println("发送验证码...");
        return userService.isRegister(phone);
    }


    //获取手机验证码
    //判断是否能注册
    @RequestMapping(value = "/getCode",method = RequestMethod.POST)
    @ResponseBody
    public String getCode(String phone){
        System.out.println("发送验证码...");
        return userService.sendValidateCode(phone);
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(User user){
        //需要发送手机号和密码
        System.out.println("用户注册...");
        System.out.println("user:"+user);
        return userService.userRegister(user);
    }

    @RequestMapping(value = "/loginForPhone",method = RequestMethod.POST)
    @ResponseBody
    public String loginForPhone(User user, HttpSession session){
        System.out.println("用户登录...");
        System.out.println("username:"+user.getUsername());
        System.out.println("passoword:"+user.getPassword());
        return userService.userLoginUsePassword(user,session);
    }
    //通过验证码登录，获取验证码
    @RequestMapping(value = "/getLoginCode",method = RequestMethod.POST)
    @ResponseBody
    public String getLoginCode(String phone){
       return userService.getValidateCodeByPhone(phone);
    }

    //通过验证码登录
    @RequestMapping(value = "/loginForCode",method = RequestMethod.POST)
    @ResponseBody
    public String loginForCode(User user, HttpSession session,String code){
        System.out.println("用户登录...");
        System.out.println("username:"+user.getUsername());
        System.out.println("passoword:"+user.getPassword());
        return userService.userLoginUseValidateCode(user,session,code);
    }
    //获取更新密码
    @RequestMapping(value = "/getForgetPwdCode",method = RequestMethod.POST)
    @ResponseBody
    public String getBackPassword(String phone){
        System.out.println("忘记密码");
        return userService.getBackPassword(phone);
    }
    //更新密码
    @RequestMapping(value = "/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    public String getBackPassword(User user,String code){
        System.out.println("忘记密码");
        return userService.updatePwd(user,code);
    }

    //更新密码
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfo(User user){
        System.out.println("更新用户信息");
        return userService.updateUserInfo(user);
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(HttpSession session){
        System.out.println("用户退出...");
        return userService.userLogOut(session);
    }
    //用户上传图片
    @RequestMapping(value = "/uploadUserPhoto",method = RequestMethod.POST)
    @ResponseBody
    public String logout(MultipartFile file, HttpServletRequest request, User user){
        System.out.println("用户上传图片...");
        return userService.uploadUserPhoto(file,request,user);
    }






}
