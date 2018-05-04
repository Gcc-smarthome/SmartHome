package it.caoxin.smarthome.domain.mapper.scene;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import it.caoxin.smarthome.domain.model.Scene;

import javax.persistence.criteria.CriteriaBuilder;

@Mapper
public interface SceneMapper {
    int insert(@Param("scene") Scene scene);

    int insertSelective(@Param("scene") Scene scene);

    int insertList(@Param("scenes") List<Scene> scenes);

    int update(@Param("scene") Scene scene);

    Scene selectByUserIdAndFamilyIdAndDeviceId(Integer familyId, Integer deviceId, Integer userId);

    int deleteSceneById(Integer sceneId);

    List<Scene> selectSceneByUserId(Integer userId);

    Scene selectSceneById(Integer id);

}
