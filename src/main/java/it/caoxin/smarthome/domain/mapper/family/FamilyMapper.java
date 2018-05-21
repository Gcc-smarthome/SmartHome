package it.caoxin.smarthome.domain.mapper.family;

import it.caoxin.smarthome.domain.model.Family;

import java.util.List;

public interface FamilyMapper {
    int deleteById(Integer id);

    int insert(Family family);

    int insertSelective(Family family);

    Family selectById(Integer id);

    Family selectByIdForUpdate(Integer id);

    Family selectByUniqueCodeForUpdate(String uniqueCode);

    int updateByIdSelective(Family family);

    int updateById(Family family);

    Family selectByUniqueCode(String uniqueCode);

    //管理员查询所有庭
    List<Family> getAllFamily();

    //通过家庭名字模糊查询
    List<Family> getAllFamilyLikeName(String name);

    //查询家庭条数
    Integer getCount();

}