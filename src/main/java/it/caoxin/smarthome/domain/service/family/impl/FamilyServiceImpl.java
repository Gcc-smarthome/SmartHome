package it.caoxin.smarthome.domain.service.family.impl;

import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.app.controller.family.FamilyController;
import it.caoxin.smarthome.domain.common.PageBean;
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
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(FamilyServiceImpl.class);
    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private UserFamilyMapper userFamilyMapper;

    @Autowired
    private FamilyImgMapper  familyImgMapper;

    @Autowired
    private UserMapper userMapper;

    private  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss");

    private static String defaultImg = "/upload/family/";
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
                if (!img.getImgUrl().equals("get photo failure")){
                    family.setPhoto(img.getImgUrl());
                }
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

        //添加默认家庭图片
//        http://120.79.21.193/SmartHome/upload/family/bg01.jpg
        for (int i = 1; i < 6; i++){
            FamilyImg familyImg = new FamilyImg();
            familyImg.setStatus(FamilyImg.STATUS_SYSTEM);
            familyImg.setFamilyId(family.getId());
            String imgurl = "bg0";
            String suffix = ".jpg";
            familyImg.setImgUrl(defaultImg+imgurl+i+suffix);

            familyImgMapper.insertSelective(familyImg);
        }
        userFamilyMapper.insert(userFamily);

        return "createFamilySuccess";
    }

    //加入家庭 管理员
    @Override
    public String joinFaimlyOfManager(User user, Family family,String validateCode) {

        if (isFamilyManager(user,family)){
            UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), family.getId());
                if (userFamily != null) {
                    userFamily.setUniqueCode(validateCode);
                    userFamilyMapper.updateSelect(userFamily);
                    return "addVaildateCodeSuccess";
                }
                return "addVaildateCodeFailure";
            }else {
                return "this user is not a manager";
            }

    }

    //加入家庭 成员
    @Override
    public String joinFamilyOfMember(User user,String uniqueCode)  {
        System.out.println("user:"+user.getId());
        System.out.println("uniqueCode:"+uniqueCode);
        Family joinFamily = null;
        if (uniqueCode != null && uniqueCode.length() > 0) {
            //通过查询出userFamily来拿到家庭id
            UserFamily userFamilyUnique = userFamilyMapper.selectByUniqueCode(uniqueCode);
            if (userFamilyUnique != null) {
                joinFamily = familyMapper.selectById(userFamilyUnique.getFamilyId());

                if (joinFamily != null) {
                    if (user.getId() != null && joinFamily.getId() != null) {
                        //在用户家庭表中查询是否存在这个用户，如果已经存在则告诉用户不能重复加入家庭
                        UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), joinFamily.getId());
                        if (userFamily != null) {
                            return "已加入家庭，不能重复加入";
                        }

                        if (userFamilyUnique.getUniqueCode().equals(uniqueCode)) {
                            UserFamily newUserFamily = new UserFamily();
                            newUserFamily.setFamilyId(joinFamily.getId());
                            newUserFamily.setUserId(user.getId());
                            newUserFamily.setFamilyRole(UserFamily.ROLE_MEMBER);
                            newUserFamily.setStatus(UserFamily.STATUS_NORMAL);

                            userFamilyMapper.insertSelective(newUserFamily);
                            return "joinFamilySuccess";
                        }
                    }
                } else {
                    return "can not find family";
                }
            }else {
                return "can not find family by uniquecode";
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

    @Override
    public String getAllFamily(Integer index) {
        PageBean<Family> pageBean = new PageBean<>();
        pageBean.setTotal(familyMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<Family> allFamily = familyMapper.getAllFamily();
        pageBean.setPage(index);
        pageBean.setBeanList(allFamily);

        JSONObject bean = JSONObject.fromObject(pageBean);
        return bean.toString();
    }

    @Override
    public String fuzzyGetFamilyByName(String name,Integer index) {

        PageBean<Family> pageBean = new PageBean<>();
        pageBean.setTotal(familyMapper.getAllFamilyLikeName(name).size());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<Family> allFamilyLikeName = familyMapper.getAllFamilyLikeName(name);
        pageBean.setPage(index);
        pageBean.setBeanList(allFamilyLikeName);

        JSONObject bean = JSONObject.fromObject(pageBean);

        return bean.toString();

    }

    @Override
    public String mDeleteFamily(Integer familyId) {
        if (familyId != null) {
            System.out.println("familyId:"+familyId);
            familyMapper.deleteById(familyId);
            return "delete Success";
        }
        return "id can not be null";
    }

    @Override
    public String getFamilys() {
        List<Family> allFamily = familyMapper.getAllFamily();
        return JSONArray.fromObject(allFamily).toString();
    }


}
