package it.caoxin.smarthome.app.controller.manager.family;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.Sensor;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FamilyManagerController {
    @Autowired
    private FamilyService familyService;

    @RequestMapping("mget_family")
    @ResponseBody
    public String getAllFamily(Integer index){
        return familyService.getAllFamily(index);
    }

    @RequestMapping("mfuzzyget_family")
    @ResponseBody
    public String fuzzyGetAllFamily(String name,Integer index){
        return familyService.fuzzyGetFamilyByName(name,index);
    }

    @PostMapping("mdelete_family")
    @ResponseBody
    public String deleteUser(@RequestBody Family family){
        System.out.println("family:"+family.getId());
        return familyService.mDeleteFamily(family.getId());
    }

    @RequestMapping("mget_familys")
    @ResponseBody
    public String getFamilys(){
        return familyService.getFamilys();
    }
}
