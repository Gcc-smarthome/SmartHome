package it.caoxin.smarthome.domain.service.scene;

import java.util.List;
import it.caoxin.smarthome.domain.model.Scene;
public interface SceneService{

    int insert(Scene scene);

    int insertSelective(Scene scene);

    int insertList(List<Scene> scenes);

    int update(Scene scene);
    /*
    添加场景
    */
    String  addScene(Scene scene);
    /*
    查询场景
     */
    String findSceneById(Scene scene);
    /*
    删除场景
     */
    String deleteScene(Scene scene);
    /*
    通过UserId查找场景
     */
    String findSceneByUserId(Integer userId);


}
