package it.caoxin.smarthome.domain.service.user;

import it.caoxin.smarthome.domain.model.User;

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

   String userLoginUseValidateCode(User user,HttpSession session);
   /*
   发送验证码
    */
   String sendValidateCode();

    /*
     用户退出
    * */

   String userLogOut(HttpSession session);

    /*
    找回密码
     */
   String getBackPassword(String phone);

    /*
    修改资料
     */
    String updateUserInfo(User user);

    /*
    通过手机获得验证码
     */
    String getValidateCodeByPhone(String phone);

}
