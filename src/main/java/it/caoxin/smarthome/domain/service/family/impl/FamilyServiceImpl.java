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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("familyService")
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private UserFamilyMapper userFamilyMapper;

    @Autowired
    private FamilyImgMapper  familyImgMapper;

    private  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss");
    //创建家庭
    @Override
    public String createFamily(User user, Family family) {
        if (user.getId() != null){
            return "无法获取用户主标识";
        }
        if (family.getFamilyName() != null){
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

    //加入家庭管理员
    @Override
    public String joinFaimlyOfManager(User user, Family family) {

        if (user.getId() != null && family.getId() != null){
            UserFamily userFamily = userFamilyMapper.selectByUserIdAndFamilyId(user.getId(), family.getId());

            //如果该用户是管理员。
            if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER)){
                //判断这个家庭的特殊码是否为空
                Family validateFamily = familyMapper.selectById(family.getId());
                if (validateFamily.getFamilyUniqueCode() == null){
                    //获取当前时间
                    String dateTime = sf.format(new Date());
                    System.out.println("dateTime"+dateTime);

                    //获取验证码
                    String validateCode = SendValidateCode.testCode();

                    //组装成特殊码
                    String uniqueCode = dateTime+":"+validateCode;

                    //放入数据库
                    validateFamily.setFamilyUniqueCode(uniqueCode);
                    familyMapper.updateByIdSelective(validateFamily);
                }else {
                    return "getValidateFailure";//提示另外的管理员正在操做，暂时无法加入该家庭
                }
            }else {
                return "this user is not a manager";
            }
        }
        return "joinFailure";
    }

    //加入家庭成员
    @Override
    public String joinFamilyOfMember(User user,String uniqueCode)  {
        //拿到当前时间
        Date dateTimeOfMember = new Date();
        Family joinFamily = familyMapper.selectByUniqueCode(uniqueCode);

        String dateTime = null;
        //解析家庭中的uniqueCode
        if (joinFamily.getFamilyUniqueCode() != null && joinFamily.getFamilyUniqueCode().length() > 0){

            String familyUniqueCode = joinFamily.getFamilyUniqueCode();
            String[] fields = familyUniqueCode.split(":");
            dateTime = fields[0];
        }

        Date dateTimeOfManager = null;
        try {
             dateTimeOfManager = sf.parse(dateTime);
            long time = (dateTimeOfMember.getTime() - dateTimeOfManager.getTime()) / (1000 * 60);
            System.out.println("time:"+time);
            if (time <= 6){
                if (joinFamily.getFamilyUniqueCode().equals(uniqueCode)){
                    if (user.getId() != null){
                        UserFamily userFamily = new UserFamily();
                        userFamily.setFamilyId(joinFamily.getId());
                        userFamily.setUserId(user.getId());
                        userFamily.setFamilyRole(UserFamily.ROLE_MEMBER);
                        userFamily.setStatus(UserFamily.STATUS_NORMAL);

                        userFamilyMapper.insertSelective(userFamily);
                        return "joinFamilySuccess";
                    }
                }else{
                    return "validateCodenotTrue";//验证码不正确
                }
            }else {
                return "valudateCodeOverDue";//验证码过期
            }
        } catch (ParseException e) {
            e.printStackTrace();
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
            if (userFamily.getFamilyRole().equals(UserFamily.ROLE_MANAGER)){
                return true;
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
            return "deleteFamilySuccess";
        }
        return "deleteFamilyFailure";
    }

    //添加家庭图片
    @Override
    public String addFamilyImg(User user, Family family, FamilyImg familyImg) {
        if (isFamilyManager(user,family)){
            if (familyImg != null){
                familyImg.setFamilyId(family.getId());
                familyImg.setStatus(FamilyImg.STATUS_NORMAL);

                familyImgMapper.insertSelective(familyImg);
                return "addFamilyImgSuccess";
            }
        }
        return "addFamilyImgFailure";
    }

    //删除家庭图片
    @Override
    public String deleteFamilyImg(User user, Family family, FamilyImg familyImg) {
        if (isFamilyManager(user,family)){
            if (familyImg.getId() != null){
                familyImg.setStatus(FamilyImg.STATUS_DELETE);

                familyImgMapper.updateSelect(familyImg);
            }else {
                return "familyImg id is Null";
            }
        }
        return null;
    }

    //查看家庭照片
    @Override
    public String familyImgs(Family family) {
        if (family.getId() != null){
            List<FamilyImg> familyImgs = familyImgMapper.selectByFamilyId(family.getId());

            return JSONArray.fromObject(familyImgs).toString();
        }
        return "no family id";
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

    //增加家庭管理员

//    //修改家庭信息
//    @Override
//    public String SelectFamilyById(Integer id) {
//        Family family = familyMapper.selectById(id);
////        List<User> userList = userMapper.selectUserByFamilyId(id);
//
//        Map valueStack = new HashMap<String,Object>();
//
//        if(family != null) {
//           valueStack.put("family",family);
////           valueStack.put("userList",userList);
//
//            JSONArray jsonMap= JSONArray.fromObject(valueStack);
//            return jsonMap.toString();
//        }else{
//            return "not found";
//        }
//    }

//    @Override
//    public String createFaimily(Integer userId,Family family) {
//        Map<String,Object> returnMap = new HashMap<String, Object>();
//        if (userId != null && familyMapper.selectByUniqueCode(family.getFamilyUniqueCode()) == null){
//            User createFaimlyUser = userMapper.selectById(userId);
//            //判断该用户家庭外键是否为空
//            if(createFaimlyUser.getFamilyId() == null){
//                //新建一个家庭
//                family.setControlMode("手动");
//                family.setStatus("正常");
//                familyMapper.insert(family);
//                //将用户角色改成主用户将用户外键添加上去
//                createFaimlyUser.setRole(User.MAIN_MEMBER);
//                createFaimlyUser.setFamilyId(family.getId());
//                userMapper.updateByIdSelective(createFaimlyUser);
//
//                returnMap.put("status","添加成功");
//                returnMap.put("family",familyMapper.selectById(family.getId()));
//            }else{
//                return "添加失败：该用户已经拥有家庭不能添加";
//            }
//        }else {
//            return "添加失败：该用户没有传出用户主标识不能添加或家庭特定标识已经存在";
//        }
//        return JSONArray.fromObject(returnMap).toString();
//    }

//    @Override
//    public String joinFaimly(Integer userId, String familyUniqueCode) {
//        if (userId != null){
//            User joinFaimlyUser = userMapper.selectById(userId);
//            if(familyUniqueCode == null){
//                //将用户角色改成用户将用户外键添加上去
//                joinFaimlyUser.setRole(User.MEMBER);
//                joinFaimlyUser.setFamilyId(familyMapper.selectByUniqueCode(familyUniqueCode).getId());
//                userMapper.updateByIdSelective(joinFaimlyUser);
//            }else{
//                return "加入失败：家庭识别码不存在";
//            }
//        } else{
//            return "加入失败：用户主标识为空";
//        }
//
//        return "加入家庭成功";
//    }

//    @Override
//    public String updateFaimly(Family family) {
//        if (family.getId() != null){
//            familyMapper.updateByIdSelective(family);
//        } else {
//            return "修改失败:家庭没有主键标识";
//        }
//
//        return "修改成功";
//    }
}
