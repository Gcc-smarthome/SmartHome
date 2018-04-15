package it.caoxin.smarthome.domain.service.family;

import com.sun.org.apache.bcel.internal.generic.FADD;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FamilyService {
    /*
    查看家庭
     */
    String getFaimlysByUser(User user);
    /*
    创建家庭
     */
    String createFamily(User user,Family family);

    /*
    加入家庭:管理员
     */
    String joinFaimlyOfManager(User user,Family family,String validateCode);

    /*
    加入家庭:用户
     */
    String joinFamilyOfMember(User user,String uniqueCode);

    /*
    修改家庭信息
     */
    String updateFamilyInfo(User user,Family family);

    /*
    判断是否是一个家庭管理员
     */
    boolean isFamilyManager(User user,Family family);

    /*
    删除家庭
     */
    String deleteFamily(User user,Family family);

    /*
    增加家庭管理员
     */
    String addFamilyManager(User user,Family family,Integer userId);

    /*
    查看家庭用户
     */
    String getAllFamilyMember(Family family);


    /*
    通过id查看家庭
     */
    String getFamilyById(Family family);

    /*
    查看这个家庭的普通成员
     */
    String getAllNormalMember(User user,Family family);

    /*
    删除成员
     */
    String deleteNormalMember(User user,Family family,Integer delUserId);

}
