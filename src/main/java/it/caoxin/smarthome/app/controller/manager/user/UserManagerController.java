package it.caoxin.smarthome.app.controller.manager.user;

import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.user.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;

@Controller
public class UserManagerController {

    @Autowired
    private UserService userService;

    @RequestMapping("/mget_users")
    @ResponseBody
    public String getAllUser(Integer index){
        return userService.getAllUser(index);
    }

    @RequestMapping("/mget_managers")
    @ResponseBody
    public String getAllManager(Integer index){
        return userService.getAllManager(index);
    }

    @RequestMapping("/fuzzyget_users")
    @ResponseBody
    public String fuzzyGetUsers(String name,Integer index){
        return userService.getFuzzyUserByNickName(name,index);
    }

    @PostMapping("/madd_user")
    @ResponseBody
    public String addUser(@RequestBody User user){
        System.out.println("啊:"+user);
       return userService.mAddUser(user);
    }

    @PostMapping("/mupdate_user")
    @ResponseBody
    public String fuzzyGetUsers(@RequestBody User user){
        System.out.println("user:"+user);
        return userService.mUpdateUser(user);
    }

    @PostMapping("mdelete_user")
    @ResponseBody
    public String deleteUser(@RequestBody User user){
        System.out.println("user:"+user.getId());
        return userService.mDeleteUser(user.getId());
    }

    @RequestMapping("/m_isregister")
    @ResponseBody
    public String mIsRegister(@RequestBody User user){
        return userService.isRegister(user.getPhone());
    }


    //管理员上传图片
    @RequestMapping(value = "/mUploadUserPhoto",method = RequestMethod.POST)
    @ResponseBody
    public String managerUploadPhoto(MultipartFile file, HttpServletRequest request){
        System.out.println("file:"+file);
        return  userService.uploadManagerPhoto(file,request);
    }

    @RequestMapping(value = "/m_login",method = RequestMethod.POST)
    @ResponseBody
    public String managerLogin(@RequestBody User user, HttpSession session){
        System.out.println("啊:"+user);
        return userService.mLogin(user,session);
    }
}
