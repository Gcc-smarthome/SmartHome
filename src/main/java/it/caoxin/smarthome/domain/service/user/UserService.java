package it.caoxin.smarthome.domain.service.user;

import it.caoxin.smarthome.domain.model.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    int deleteById(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User user);

    int updateById(User user);

//    String userLogin(User user,HttpSession session);

    String userLogOut(HttpSession session);

//    String sendValidateCode(String phone);

    String userRegister(User user);

    String getUserInfo(Integer userId);
}
