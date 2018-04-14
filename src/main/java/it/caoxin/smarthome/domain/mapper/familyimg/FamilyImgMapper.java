package it.caoxin.smarthome.domain.mapper.familyimg;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import it.caoxin.smarthome.domain.model.FamilyImg;

@Mapper
public interface FamilyImgMapper {
    int insert(@Param("familyImg") FamilyImg familyImg);

    int insertSelective(@Param("familyImg") FamilyImg familyImg);

    int insertList(@Param("familyImgs") List<FamilyImg> familyImgs);

    int updateSelect(@Param("familyImg") FamilyImg familyImg);

    FamilyImg selectById(Integer id);

    List<FamilyImg> selectByFamilyId(Integer familyId);

    FamilyImg selectByDefaultFamilyId(Integer familyId);
}
