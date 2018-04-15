package it.caoxin.smarthome.domain.service.family.impl;

import it.caoxin.smarthome.domain.common.SendValidateCode;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.familyimg.FamilyImgMapper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.mapper.userfamily.UserFamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.model.UserFamily;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service("familyService")
public class FamilyServiceImpl implements FamilyService {


    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private UserFamilyMapper userFamilyMapper;

    @Autowired
    private FamilyImgMapper  familyImgMapper;

    @Autowired
    private UserMapper userMapper;

    private  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss");

    //查看所有家庭
    @Override
    public String getFaimlysByUser(User user) {
        List<Family> families = new ArrayList<Family>();
        List<UserFamily> userFamilies = null;
        if (user != null) {
             userFamilies = userFamilyMapper.selectByUserId(user.getId());
        }
        for (UserFamily userFamily : userFamilies){
            Family family = familyMapper.selectById(userFamily.getFamilyId());
            FamilyImg img = familyImgMapper.selectByDefaultFamilyId(family.getId());
            if (img != null){
                family.setPhoto(img.getImgUrl());
            }
            families.add(family);
        }


        return JSONArray.fromObject(families).toString();
    }

    //创建家庭
    @Override
    public String createFamily(User user, Family family) {
        if (user.getId() == null){
            return "无法获取用户主标识";
        }
        if (family.getFamilyName() == null){
            return "家庭信息不能为空";
        }
        familyMapper.insertSelective(family);
        //设置用户家庭信息表
        UserFamily userFamily = new UserFamily();
        System.out.println("familyid:"+family.getId());
        userFamily.setFamilyId(family.getId());
        userFamily.setUserId(user.getId());
        //创建家庭的为管理员
        userFamily.setFamilyRole(UserFamily.ROLE_MANAGER);
        userFamily.setStatus(UserFamily.STATUS_NORMAL);

        userFamilyMapper.insert(userFamily);

        return "createFamilySuccess";
    }

    //加入家庭 管理员
    @Override
    public String joinFaimlyOfManager(User user, Family family,String validateCode) {

        if (user.getId() != null && family.getId() != null){
            UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), family.getId());

            Family validateFamily = familyMapper.selectByIdForUpdate(family.getId());
            //如果该用户是管理员。
            if (userFamily != null){
                if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER)){
                    //判断这个家庭的特殊码是否为空
                    if (validateFamily.getFamilyUniqueCode() == null){
                        //放入数据库
                        validateFamily.setFamilyUniqueCode(validateCode);
                        familyMapper.updateByIdSelective(validateFamily);
                    }else {

                        //添加一个验证码
                        validateFamily.setFamilyUniqueCode(validateFamily.getFamilyUniqueCode()+","+validateCode);
                        familyMapper.updateByIdSelective(validateFamily);

                    }

                    return "addVaildateCodeSuccess";
                }else {
                    return "this user is not a manager";
                }
            }

        }
        return "joinFailure";
    }

    //加入家庭 成员
    @Override
    public String joinFamilyOfMember(User user,String uniqueCode)  {
        System.out.println("user:"+user.getId());
        System.out.println("uniqueCode:"+uniqueCode);
        Family joinFamily = null;
        if (uniqueCode != null && uniqueCode.length() > 0){
            System.out.println("--------------------");
             joinFamily = familyMapper.selectByUniqueCodeForUpdate(uniqueCode);

           if (joinFamily != null){
               if(user.getId() != null && joinFamily.getId() != null){
                   UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), joinFamily.getId());

                   if (userFamily != null){
                       return "已加入家庭，不能重复加入";
                   }
               }

               if (joinFamily != null){
                   //解析家庭中的uniqueCode
                   if (joinFamily.getFamilyUniqueCode() != null && joinFamily.getFamilyUniqueCode().length() > 0){

                       String familyUniqueCode = joinFamily.getFamilyUniqueCode();

                       String[]  fields = familyUniqueCode.split(",");

                       if(fields != null){
                           for (String validateCode : fields){
                               if (validateCode.equals(uniqueCode)){
                                   UserFamily userFamily = new UserFamily();
                                   userFamily.setFamilyId(joinFamily.getId());
                                   userFamily.setUserId(user.getId());
                                   userFamily.setFamilyRole(UserFamily.ROLE_MEMBER);
                                   userFamily.setStatus(UserFamily.STATUS_NORMAL);

                                   userFamilyMapper.insertSelective(userFamily);


                                   return "joinFamilySuccess";
                               }
                           }
                       } else{
                           //验证码不正确
                           return "validateCodenotTrue";
                       }
                   }
               }

            }
           }



        return "joinFamilyFailure";
    }

    //更新家庭信息
    @Override
    public String updateFamilyInfo(User user, Family family) {
        if (isFamilyManager(user,family)){
            familyMapper.updateByIdSelective(family);
            return "updateFamilyInfoSuccess";
        }
        return "updateFamilyInfoFailure";
    }
    //判断是否是一个家庭管理员
    @Override
    public boolean isFamilyManager(User user, Family family) {

        if (user.getId() != null && family.getId() != null){
            UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), family.getId());
//            System.out.println("userFamily:------------"+userFamily);
            if(userFamily != null){
                if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER) ||
                        userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER_SECOND)){
                    return true;
                }
            }

        }
        return false;
    }

    //删除家庭
    @Override
    public String deleteFamily(User user, Family family) {
        if (isFamilyManager(user,family)){
            Family deleteFamily = familyMapper.selectById(family.getId());
            deleteFamily.setStatus(Family.STATUS_DELETE);
            familyMapper.updateByIdSelective(deleteFamily);

            List<UserFamily> userFamilyList = userFamilyMapper.selectByFamilyId(family.getId());
            if (userFamilyList != null){
                for (UserFamily userfamily:userFamilyList
                     ) {
                    userfamily.setStatus(UserFamily.STATUS_DELETE);
                    userFamilyMapper.updateSelect(userfamily);
                }
            }
            return "deleteFamilySuccess";
        }
        return "deleteFamilyFailure";
    }




    //增加家庭管理员
    @Override
    public String addFamilyManager(User user, Family family, Integer userId) {
        //查询该用户是不是管理员
        if (isFamilyManager(user,family)){
            if (userId != null){
                UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(userId, family.getId());

                userFamily.setFamilyRole(UserFamily.ROLE_MANAGER);

                userFamilyMapper.updateSelect(userFamily);

                return "addManagerSuccess";
            }
        }
        return "you are no a manager";
    }
    //查看家庭中的成员
    @Override
    public String getAllFamilyMember(Family family){

        Map valueStack = new HashMap<String,List>();
        List creator = new ArrayList<User>();
        List member = new ArrayList<User>();
        List mamager = new ArrayList<User>();
        List<UserFamily> userFamilies = null;
        if (family.getId() != null) {
            userFamilies = userFamilyMapper.selectByFamilyId(family.getId());

            for (UserFamily userFamily : userFamilies){
               User selectUser = userMapper.selectById(userFamily.getUserId());
                if (selectUser != null){
                    if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER)){
                        creator.add(selectUser);
                        valueStack.put("创建者",creator);

                    }else if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER_SECOND)){
                        mamager.add(selectUser);
                        valueStack.put("管理员",mamager);
                    }else{
                        member.add(selectUser);
                        valueStack.put("普通成员",member);
                    }
                }
            }

            return JSONArray.fromObject(valueStack).toString();
        }
        return  "getUserFail";
    }

    @Override
    public String getFamilyById(Family family) {
        Family familyInfo = familyMapper.selectById(family.getId());
        FamilyImg img = familyImgMapper.selectByDefaultFamilyId(family.getId());
        if (img != null) {
            familyInfo.setPhoto(img.getImgUrl());
        }
        return JSONArray.fromObject(familyInfo).toString();
    }
    //获取普通成员
    @Override
    public String getAllNormalMember(User user, Family family) {
        if(isFamilyManager(user,family)) {
            List member = new ArrayList<User>();
            List<UserFamily> userFamilies = null;
            if (family.getId() != null) {
                userFamilies = userFamilyMapper.selectByFamilyId(family.getId());

                for (UserFamily userFamily : userFamilies) {
                    User selectUser = userMapper.selectById(userFamily.getUserId());
                    if (selectUser != null) {
                        if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MEMBER)) {
                            member.add(selectUser);
                        }
                    }
                }

                return JSONArray.fromObject(member).toString();
            }
        }
        return  "getUserFail";
    }
    //删除普通成员
    @Override
    public String deleteNormalMember(User user, Family family, Integer delUserId) {
        //先判断是否是管理员
        if(isFamilyManager(user,family)){
            UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(delUserId, family.getId());

            if (userFamily != null){
                if( userFamily.getStatus().equals(UserFamily.STATUS_NORMAL)){
                    userFamily.setStatus(UserFamily.STATUS_DELETE);

                    userFamilyMapper.updateSelect(userFamily);
                    return "delete member success";
                }
            }else {
                return "find not member";
            }
        }else {
            return "you are not a member";
        }
        return "delete member failure";
    }



}
