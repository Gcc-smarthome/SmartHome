package it.caoxin.smarthome.domain.service.family.impl;

import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.mapper.user.UserMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("familyService")
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteById(Integer id) {
        return familyMapper.deleteById(id);
    }

    @Override
    public int insert(Family family) {
        return familyMapper.insert(family);
    }

    @Override
    public int insertSelective(Family family) {
        return familyMapper.insertSelective(family);
    }


    @Override
    public int updateByIdSelective(Family family) {
        return familyMapper.updateByIdSelective(family);
    }

    @Override
    public int updateById(Family family) {
        return familyMapper.updateById(family);
    }

    @Override
    public String SelectFamilyById(Integer id) {
        Family family = familyMapper.selectById(id);
//        List<User> userList = userMapper.selectUserByFamilyId(id);

        Map valueStack = new HashMap<String,Object>();

        if(family != null) {
           valueStack.put("family",family);
//           valueStack.put("userList",userList);

            JSONArray jsonMap= JSONArray.fromObject(valueStack);
            return jsonMap.toString();
        }else{
            return "not found";
        }
    }

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

    @Override
    public String updateFaimly(Family family) {
        if (family.getId() != null){
            familyMapper.updateByIdSelective(family);
        } else {
            return "修改失败:家庭没有主键标识";
        }

        return "修改成功";
    }
}
