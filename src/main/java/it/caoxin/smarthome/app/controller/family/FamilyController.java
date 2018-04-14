package it.caoxin.smarthome.app.controller.family;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    //查看家庭
    @RequestMapping(value = "/get_family",method = RequestMethod.GET)
    @ResponseBody
    public String selectFamilyById(User user){
        return familyService.getFaimlysByUser(user);
    }


    //通过id查看家庭
    @RequestMapping(value = "/get_family/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String selectFamilyById(@PathVariable Integer id){
        Family family = new Family();
        family.setId(id);
        return familyService.getFamilyById(family);
    }
    /**
     *添加家庭
     */
    @RequestMapping(value = "/add_family",method = RequestMethod.POST)
    @ResponseBody
    public String createFamily(User user ,Family family){
        System.out.println("userId:"+user.getId());
        System.out.println("familyName:"+family.getFamilyName());
        System.out.println("创建家庭");
        return familyService.createFamily(user,family);
    }

    /**
     * 刪除家庭
     */
    @RequestMapping(value = "/del_family",method = RequestMethod.POST)
    @ResponseBody
    public String del_family(Integer userId,Integer familyId){
        System.out.println("userId:"+userId);
        System.out.println("familyId:"+userId);
        System.out.println("刪除家庭");

        User user = new User();
        user.setId(userId);
        Family family = new Family();
        family.setId(familyId);
        return familyService.deleteFamily(user,family);
    }

    /**
     * 更新家庭
     */
    @RequestMapping(value = "/ud_family",method = RequestMethod.POST)
    @ResponseBody
    public String ud_family(Integer userId,Family family){

        System.out.println("userId:"+userId);
        System.out.println("familyId:"+userId);
        System.out.println("更新i就爱听个");
        User user = new User();
        user.setId(userId);
        return familyService.updateFamilyInfo(user,family);
    }

    /**
     *
     */

//    @RequestMapping(value = "/family",method = RequestMethod.PUT)
//    @ResponseBody
//    public String joinFamily(@RequestParam Family family){
//        return familyService.updateFaimly(family);
//    }
}
