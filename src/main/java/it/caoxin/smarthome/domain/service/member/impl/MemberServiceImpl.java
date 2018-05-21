package it.caoxin.smarthome.domain.service.member.impl;

import com.github.pagehelper.PageHelper;
import it.caoxin.smarthome.domain.common.PageBean;
import it.caoxin.smarthome.domain.mapper.userfamily.UserFamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.UserFamily;
import it.caoxin.smarthome.domain.service.member.MemberService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private UserFamilyMapper userFamilyMapper;
    @Override
    public String selectUserFamilyByUserId(Integer userId,Integer index) {
        if (userId != null){
            PageBean<UserFamily> pageBean = new PageBean<>();
            pageBean.setTotal(userFamilyMapper.selectByUserId(userId).size());

            if (index == null){
                index = 1;
            }
            PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
            List<UserFamily> allMember = userFamilyMapper.selectByUserId(userId);
            pageBean.setPage(index);
            pageBean.setBeanList(allMember);

            JSONObject bean = JSONObject.fromObject(pageBean);
            return bean.toString();

        }

        return getAllUserFamily(index);
    }

    @Override
    public String selectUserFamliyByFamilyId(Integer familyId,Integer index) {
        if (familyId != null){
            PageBean<UserFamily> pageBean = new PageBean<>();
            pageBean.setTotal(userFamilyMapper.selectByFamilyId(familyId).size());

            if (index == null){
                index = 1;
            }
            PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
            List<UserFamily> allMember = userFamilyMapper.selectByFamilyId(familyId);
            pageBean.setPage(index);
            pageBean.setBeanList(allMember);

            JSONObject bean = JSONObject.fromObject(pageBean);
            return bean.toString();

        }

        return getAllUserFamily(index);
    }

    @Override
    public String getAllUserFamily(Integer index) {
        PageBean<UserFamily> pageBean = new PageBean<>();
        pageBean.setTotal(userFamilyMapper.getCount());

        if (index == null){
            index = 1;
        }
        PageHelper.startPage(index, pageBean.getPageSize());//指定开始分页
        List<UserFamily> allMember = userFamilyMapper.getAllMember();
        pageBean.setPage(index);
        pageBean.setBeanList(allMember);

        JSONObject bean = JSONObject.fromObject(pageBean);
        return bean.toString();
    }
}
