package it.caoxin.smarthome.domain.service.family;

import com.sun.org.apache.bcel.internal.generic.FADD;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;

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
    String joinFaimlyOfManager(User user,Family family);

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
    添加家庭图片
     */
    String addFamilyImg(User user, Family family, FamilyImg familyImg);

    /*
    删除家庭图片
     */
    String deleteFamilyImg(User user, Family family, FamilyImg familyImg);

    /*
    查看家庭图片
     */
    String familyImgs(Family family);

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
//    String SelectFamilyById(Integer id);


}
