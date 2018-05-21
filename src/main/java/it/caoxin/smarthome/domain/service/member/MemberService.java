package it.caoxin.smarthome.domain.service.member;

import org.springframework.stereotype.Service;


public interface MemberService {
    //通过用户id查询家庭成员
    String selectUserFamilyByUserId(Integer userId,Integer index);
    //通过家庭通过用户id查看家庭成员
    String selectUserFamliyByFamilyId(Integer familyId,Integer index);
    //查看全部家庭成员
    String getAllUserFamily(Integer index);
}
