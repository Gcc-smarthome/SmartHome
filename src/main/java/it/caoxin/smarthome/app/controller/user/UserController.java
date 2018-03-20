package it.caoxin.smarthome.app.controller.user;

import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *@Author:Caoxin
     *@Description:用户登录
     *@Date:9:13 2018/2/2
     *@param[user, session]
     *@returnjava.lang.String
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(User user, HttpSession session){
        System.out.println("用户登录...");
        System.out.println("username:"+user.getUsername());
        System.out.println("passoword:"+user.getPassword());
        return userService.userLogin(user,session);
    }

    /**
     *@Author:Caoxin
     *@Description:用户退出
     *@Date:9:22 2018/2/2
     *@param[session]
     *@returnjava.lang.String
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession session){
        System.out.println("用户退出...");
        return userService.userLogOut(session);
    }

    @RequestMapping(value = "/validateCode",method = RequestMethod.GET)
    @ResponseBody
    public String validateCode(@RequestParam String phone){
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

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(@PathVariable Integer userId){

        return userService.getUserInfo(userId);
    }



}
