package it.caoxin.smarthome.domain.mapper.user;


import it.caoxin.smarthome.domain.model.User;

import java.util.List;

public interface UserMapper {
    int deleteById(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User user);

    int updateById(User user);

    User selectUserByPhone(String phone);

    User selectByPhoneAndPassword(User user);

    User selectByUsernameAndPassword(User user);

    //查询所有用户
    List<User> getAllUser();
    //通过姓名模糊查询
    List<User> getAllUserLikeName(String name);
    //查询全部管理员
    List<User> getAllManager();

    Integer getCount();

    Integer getCountManager();

    User selectManagerByPhone(String phone);

}