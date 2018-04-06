package it.caoxin.smarthome.domain.service.familyimg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.mapper.familyimg.FamilyImgMapper;
import it.caoxin.smarthome.domain.service.familyimg.FamilyImgService;

@Service
public class FamilyImgServiceImpl implements FamilyImgService{

    @Resource
    private FamilyImgMapper familyImgMapper;

    @Override
    public int insert(FamilyImg familyImg){
        return familyImgMapper.insert(familyImg);
    }

    @Override
    public int insertSelective(FamilyImg familyImg){
        return familyImgMapper.insertSelective(familyImg);
    }

    @Override
    public int insertList(List<FamilyImg> familyImgs){
        return familyImgMapper.insertList(familyImgs);
    }

    @Override
    public int updateSelect(FamilyImg familyImg){
        return familyImgMapper.updateSelect(familyImg);
    }
}
