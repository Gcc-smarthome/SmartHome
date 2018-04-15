package it.caoxin.smarthome.domain.service.familyimg;

import java.util.List;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FamilyImgService{

    int insert(FamilyImg familyImg);

    int insertSelective(FamilyImg familyImg);

    int insertList(List<FamilyImg> familyImgs);

    int updateSelect(FamilyImg familyImg);

    /*
添加家庭图片
 */
    String addFamilyImg(User user, Family family,
                        MultipartFile[] files,
                        HttpServletRequest request);

    /*
    删除家庭图片
     */
    String deleteFamilyImg(User user, Family family, FamilyImg familyImg);

    /*
  查看家庭图片
   */
    String familyImgs(Family family);


}
