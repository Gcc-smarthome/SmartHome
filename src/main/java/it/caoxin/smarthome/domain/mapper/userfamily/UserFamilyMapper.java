package it.caoxin.smarthome.domain.mapper.userfamily;

import it.caoxin.smarthome.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import it.caoxin.smarthome.domain.model.UserFamily;

@Mapper
public interface UserFamilyMapper {
    int insert(@Param("userFamily") UserFamily userFamily);

    int insertSelective(@Param("userFamily") UserFamily userFamily);

    int insertList(@Param("userFamilys") List<UserFamily> userFamilys);

    int updateSelect(@Param("userFamily") UserFamily userFamily);

    UserFamily selectById(Integer id);

    UserFamily selectByUserIdAndFamilyId(Integer userId,Integer familyId);

    List<UserFamily> selectByUserId(Integer userId);

    List<UserFamily> selectByFamilyId(Integer userId);
}
