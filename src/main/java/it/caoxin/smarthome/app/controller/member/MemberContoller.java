package it.caoxin.smarthome.app.controller.member;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.model.UserFamily;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberContoller {
    @Autowired
    private FamilyService familyService;

    //生成家庭特殊码
    @RequestMapping(value = "/add_family_uniquecode",method = RequestMethod.POST)
    @ResponseBody
    public String addFamilyUniquecode(Integer userId,Integer familyId,String uniqueCode){
        //获取不到uniqueCode
        System.out.println("uniqueCode:"+uniqueCode);
        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);
        return familyService.joinFaimlyOfManager(user,family,uniqueCode);
    }
    //加入家庭
    @RequestMapping(value = "/add_member",method = RequestMethod.POST)
    @ResponseBody
    public String addFamilyMember(Integer userId,String uniqueCode){
        User user = new User();
        user.setId(userId);
        return familyService.joinFamilyOfMember(user,uniqueCode);
    }

    //获得所有家庭成员
    @RequestMapping(value = "/get_member",method = RequestMethod.GET)
    @ResponseBody
    public String getFamilyMember(Family family){

        return familyService.getAllFamilyMember(family);
    }

    //获得家庭中普通成员
    @RequestMapping(value = "/get_normal_member",method = RequestMethod.GET)
    @ResponseBody
    public String getNormalMember(Integer userId,Integer familyId){
        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        return familyService.getAllNormalMember(user,family);
    }

    //删除家庭成员
    @RequestMapping(value = "/del_member",method = RequestMethod.POST)
    @ResponseBody
    public String deleteMemer(Integer userId,Integer familyId,Integer deleteUserId){
        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);
        System.out.println("userId:----------------------"+userId);
        System.out.println("FamilyId:----------------------"+familyId);
        System.out.println("delId:----------------------"+deleteUserId);
        return familyService.deleteNormalMember(user,family,deleteUserId);
    }

    @RequestMapping(value = "/is_manager",method = RequestMethod.POST)
    @ResponseBody
    public String deleteMemer(Integer userId,Integer familyId){
        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        return familyService.isFamilyManager(user,family)+"";
    }


}
