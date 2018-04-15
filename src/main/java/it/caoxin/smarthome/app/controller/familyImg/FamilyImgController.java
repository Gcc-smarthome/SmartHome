package it.caoxin.smarthome.app.controller.familyImg;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import it.caoxin.smarthome.domain.service.familyimg.FamilyImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FamilyImgController {
    @Autowired
    private FamilyImgService familyImgService;

    //给家庭添加照片
    @RequestMapping(value = "/add_familyimg",method = RequestMethod.POST)
    @ResponseBody
    public String addFamilyImg(Integer userId,Integer familyId,
                               MultipartFile[] files,
                               HttpServletRequest request){
        System.out.println("userId:"+userId);
        System.out.println("files:"+files);
        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);
        return familyImgService.addFamilyImg(user,family,
                files,
                request);
    }
}