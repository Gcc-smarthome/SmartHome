package it.caoxin.smarthome.app.controller.manager.member;

import it.caoxin.smarthome.domain.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberManagerController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("mget_member")
    @ResponseBody
    public String getAllUserFamily(Integer index){
        return memberService.getAllUserFamily(index);
    }

    @RequestMapping("mget_memberbyuserid")
    @ResponseBody
    public String getUserFamilyByUserId(Integer userId,Integer index){
      return memberService.selectUserFamilyByUserId(userId,index);
    }

    @RequestMapping("mget_memberbyfamilyid")
    @ResponseBody
    public String getUserFamilyByFamilyId(Integer familyId,Integer index){
        return memberService.selectUserFamliyByFamilyId(familyId,index);
    }
}
