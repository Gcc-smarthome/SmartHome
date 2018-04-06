package it.caoxin.smarthome.app.controller.family;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    /**
     * 查询对应的家庭
     * @param
     * @return
     */
    @RequestMapping(value = "/family/{familyId}",method = RequestMethod.GET)
    @ResponseBody
    public String selectFamilyById(@PathVariable Integer familyId){
        return familyService.SelectFamilyById(familyId);
    }


    /**
     *添加家庭
     * @param userId
     * @param family
     * @return 家庭是否添加成功
     */
    @RequestMapping(value = "/family/{userId}",method = RequestMethod.POST)
    @ResponseBody
    public String createFamily(@PathVariable Integer userId,
                                Family family){
        System.out.println("userId:"+userId);
        System.out.println("family:"+family);
        return "familyService.createFaimily(userId,family)";
    }

    @RequestMapping(value = "/family/{userId}",method = RequestMethod.PUT)
    @ResponseBody
    public String joinFamily(@PathVariable Integer userId,
                               @RequestParam String familyUniqueCode){
        return "familyService.joinFaimly(userId,familyUniqueCode)";
    }

    @RequestMapping(value = "/family",method = RequestMethod.PUT)
    @ResponseBody
    public String joinFamily(@RequestParam Family family){
        return familyService.updateFaimly(family);
    }
}
