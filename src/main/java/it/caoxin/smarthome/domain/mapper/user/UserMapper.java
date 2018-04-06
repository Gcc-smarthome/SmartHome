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
}