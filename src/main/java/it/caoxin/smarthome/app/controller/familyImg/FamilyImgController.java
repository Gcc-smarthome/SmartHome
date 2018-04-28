package it.caoxin.smarthome.app.controller.familyImg;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import it.caoxin.smarthome.domain.service.familyimg.FamilyImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FamilyImgController {
    public static final Logger logger = LoggerFactory.getLogger(FamilyImgController.class);
    @Autowired
    private FamilyImgService familyImgService;

    //给家庭添加照片
    @RequestMapping(value = "/add_familyimg",method = RequestMethod.POST)
    @ResponseBody
    public String addFamilyImg(Integer userId,Integer familyId,
                              @RequestParam MultipartFile[] files,
                               HttpServletRequest request){
        System.out.println("userId:"+userId);


        if (files != null && files.length != 0){
            logger.debug("添加图片的文件file不为空");
            logger.debug("file:"+files);
        }else {
            logger.debug("files为空");
        }


        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);
        return familyImgService.addFamilyImg(user,family,
                files,
                request);
    }

    //给家庭删除照片
    @RequestMapping(value = "/del_familyimg",method = RequestMethod.POST)
    @ResponseBody
    public String addFamilyImg(Integer userId,Integer familyId,Integer familyImgId){

        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        FamilyImg familyImg = new FamilyImg();
        familyImg.setId(familyImgId);

        return familyImgService.deleteFamilyImg(user,family,familyImg);
    }

    //获取家庭照片:需要改的地方，不管是不是家庭管理员都可以查看
    @RequestMapping(value = "/get_familyimg",method = RequestMethod.GET)
    @ResponseBody
    public String getFamilyImg(Integer userId,Integer familyId){

        User user = new User();
        user.setId(userId);

        Family family = new Family();
        family.setId(familyId);

        return familyImgService.getfamilyImgs(family,user);
    }
    //更加家庭图片
    @RequestMapping(value = "/ud_familyimg",method = RequestMethod.POST)
    @ResponseBody
    public String updateFamilyImg(Integer familyImgId,Integer familyId){

        FamilyImg familyImg = new FamilyImg();
        familyImg.setId(familyImgId);

        Family family = new Family();
        family.setId(familyId);

        return familyImgService.updateFamilyImg(familyImg,family);
    }

    //获取系统照片
    @RequestMapping(value = "/get_sysimg",method = RequestMethod.GET)
    @ResponseBody
    public String getSysImg(Integer familyId){


        Family family = new Family();
        family.setId(familyId);

        return familyImgService.getSystePhoto(family);
    }
}
