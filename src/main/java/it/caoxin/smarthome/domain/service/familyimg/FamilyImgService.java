package it.caoxin.smarthome.domain.service.familyimg;

import java.util.List;
import it.caoxin.smarthome.domain.model.FamilyImg;
public interface FamilyImgService{

    int insert(FamilyImg familyImg);

    int insertSelective(FamilyImg familyImg);

    int insertList(List<FamilyImg> familyImgs);

    int updateSelect(FamilyImg familyImg);
}
