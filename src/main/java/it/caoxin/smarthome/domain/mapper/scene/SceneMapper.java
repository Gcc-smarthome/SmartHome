package it.caoxin.smarthome.domain.mapper.scene;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import it.caoxin.smarthome.domain.model.Scene;

@Mapper
public interface SceneMapper {
    int insert(@Param("scene") Scene scene);

    int insertSelective(@Param("scene") Scene scene);

    int insertList(@Param("scenes") List<Scene> scenes);

    int update(@Param("scene") Scene scene);
}
