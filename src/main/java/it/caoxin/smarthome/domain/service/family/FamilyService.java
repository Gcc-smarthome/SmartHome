package it.caoxin.smarthome.domain.service.family;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;

public interface FamilyService {
    int deleteById(Integer id);

    int insert(Family family);

    int insertSelective(Family family);

    int updateByIdSelective(Family family);

    int updateById(Family family);

    String SelectFamilyById(Integer id);

//    String createFaimily(Integer userId,Family faimly);

//    String joinFaimly(Integer userId,String familyUniqueCode);

    String updateFaimly(Family family);
}
