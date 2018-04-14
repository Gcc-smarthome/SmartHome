package it.caoxin.smarthome.domain.mapper.family;

import it.caoxin.smarthome.domain.model.Family;

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

}