package it.caoxin.smarthome.domain.service.user;

import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    /*
        判断该手机是否已被注册
     */
     String isRegister(String phone);
     /*
     用户注册
      */
     String userRegister(User user);

     /*
     用户登录方式一：手机和密码登录
      */
   String userLoginUsePassword(User user,HttpSession session);

   /*
   登录方式二：验证码登录
    */

   String userLoginUseValidateCode(User user,HttpSession session,String code);
   /*
   发送验证码
    */
   String sendValidateCode(String phone);

    /*
     用户退出
    * */

   String userLogOut(HttpSession session);

    /*
    找回密码
     */
   String getBackPassword(String phone);

   /*
   用户修改密码
    */
   String updatePwd(User user,String code);
    /*
    修改资料
     */
    String updateUserInfo(User user);

    /*
    通过手机获得验证码
     */
    String getValidateCodeByPhone(String phone);

    /*
    用户上传图片
     */
    String uploadUserPhoto(MultipartFile file, HttpServletRequest request,User user);

    /*
    管理员获取用户
     */
    String getAllUser(Integer page);

    /*
    管理员获取管理员列表
     */
    String getAllManager(Integer index);

    /*
    通过名字模糊查询模糊查询
     */
    String getFuzzyUserByNickName(String name,Integer index);

    /*
    查询总数
     */
    Integer getUserCount();

    /*
    管理员添加用户
     */
    String mAddUser(User user);

    /*
    管理员删除用户
     */
    String mDeleteUser(Integer userIds);

    /*
    管理员修改用户
     */
    String mUpdateUser(User user);

    /*
    管理员验证手机能否注册注册
     */
    String managerIsRegister(String username);

    /*
    管理员注册
     */
    String managerRegister(User user);

    /*
    管理员登陆
     */
    String mLogin(User user,HttpSession session);

    /*
    管理员上传图片
     */
    String uploadManagerPhoto(MultipartFile file, HttpServletRequest request);
}
