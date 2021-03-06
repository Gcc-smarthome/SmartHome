package it.caoxin.smarthome.domain.service.user.impl;



import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.domain.common.PageBean;
import it.caoxin.smarthome.domain.common.SendValidateCode;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.user.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author caoxin
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final String userPhotoPath = "/upload/user/";

    public static final Logger logger = LoggerFactory.getLogger("DeviceContoller");
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

//            logger.info("user_login"+" "+Integer.MIN_VALUE+" "+Integer.MIN_VALUE+" "+user.getPhone()+" "+"login");

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

//                    logger.info("user_login"+" "+Integer.MIN_VALUE+" "+Integer.MIN_VALUE+" "+user.getPhone()+" "+"login");

                    return json.toString();//登录成功返回User对象
                }

            }
        }
        //前端获取验证码成功时，请求登录，如果用户不存在则cann't login提示用户需要注册
        return "cann'tLogin";
    }

    //发送验证码
    @Override
    public String sendValidateCode(String phone) {
        return SendValidateCode.sendSms(phone);
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



                String code = SendValidateCode.sendSms(phone);
                getValidateCodeUser.setCode(code);

                userMapper.updateByIdSelective(getValidateCodeUser);

                return code;
            }
        }
        return "getValidateCodeFailure";//提示没有对应的手机用户
    }

    //用户上传图片
    @Override
    public String uploadUserPhoto(MultipartFile file, HttpServletRequest request, User user) {
        //上传文件：
        String uploadPath = request.getServletContext().getRealPath("/upload/user");


        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID()+originalFilename.substring(originalFilename.length()-4,originalFilename.length());
        if (!originalFilename.endsWith(".jpg") &&
                !originalFilename.endsWith(".bmp") &&
                !originalFilename.endsWith(".jpeg")&&
                !originalFilename.endsWith(".png")){
            return "Illegal file format";
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath,fileName));
            System.out.print("上传路径是："+uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("添加图片出错。。。");
        }

        String photoUrl = userPhotoPath + fileName;
        user.setPhoto(photoUrl);
        userMapper.updateByIdSelective(user);

        Map valueStack = new HashMap();
        User updateUser = userMapper.selectById(user.getId());
        valueStack.put("user",updateUser);
        return JSONArray.fromObject(valueStack).toString();

    }

    @Override
    public String getAllUser(Integer index) {


        PageBean<User> pageBean = new PageBean<>();
        pageBean.setTotal(userMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<User> allUser = userMapper.getAllUser();
        pageBean.setPage(index);
        pageBean.setBeanList(allUser);

        JSONObject bean = JSONObject.fromObject(pageBean);

        return bean.toString();
    }

    @Override
    public String getAllManager(Integer index) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setTotal(userMapper.getCountManager());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<User> allUser = userMapper.getAllManager();
        pageBean.setPage(index);
        pageBean.setBeanList(allUser);

        JSONObject bean = JSONObject.fromObject(pageBean);

        return bean.toString();
    }

    @Override
    public String getFuzzyUserByNickName(String name,Integer index) {


        PageBean<User> pageBean = new PageBean<>();
        pageBean.setTotal(userMapper.getAllUserLikeName(name).size());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<User> allUser = userMapper.getAllUserLikeName(name);
        pageBean.setPage(index);
        pageBean.setBeanList(allUser);

        JSONObject bean = JSONObject.fromObject(pageBean);

        return bean.toString();
    }

    @Override
    public Integer getUserCount() {
        return userMapper.getCount();
    }

    @Override
    public String mAddUser(User user) {
        //判断用户是否已经注册
        if (user != null){
            String register = isRegister(user.getPhone());
            if (register.equals("validSuccess")){
                user.setSex("男");
                user.setNickname("管理员"+UUID.randomUUID().toString());
                user.setRegisterTime(new Date());
                userMapper.insertSelective(user);
                return "add Success";
            }else {
                return "the phone is registered";
            }
        }
        return "user is null";
    }

    @Override
    public String mDeleteUser(Integer userIds) {
        if (userIds != null) {
            System.out.println("userIds:"+userIds);
            userMapper.deleteById(userIds);
            return "delete Success";
        }
        return "id can not be null";
    }

    @Override
    public String mUpdateUser(User user) {
        if (user.getId() != null){
            userMapper.updateByIdSelective(user);
            return "update Success";
        }
        return "id can not be null";
    }

    @Override
    public String managerIsRegister(String phone) {
        return isRegister(phone);
    }

    @Override
    public String managerRegister(User user) {
        //添加插入用户信息，图片上传。
        //将用户设置成管理员
        return null;
    }

    @Override
    public String mLogin(User user, HttpSession session) {
        return userLoginUsePassword(user,session);
    }

    @Override
    public String uploadManagerPhoto(MultipartFile file, HttpServletRequest request) {
        //上传文件：
        String uploadPath = request.getServletContext().getRealPath("/upload/user");


        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID()+originalFilename.substring(originalFilename.length()-4,originalFilename.length());
        if (!originalFilename.endsWith(".jpg") &&
                !originalFilename.endsWith(".bmp") &&
                !originalFilename.endsWith(".jpeg")&&
                !originalFilename.endsWith(".png")){
            return "Illegal file format";
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(uploadPath,fileName));
            System.out.print("上传路径是："+uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("添加图片出错。。。");
        }

        String photoUrl = userPhotoPath + fileName;
        return photoUrl;
    }


}
