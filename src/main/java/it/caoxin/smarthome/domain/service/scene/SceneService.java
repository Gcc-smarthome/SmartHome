package it.caoxin.smarthome.domain.service.scene;

import java.util.List;
import it.caoxin.smarthome.domain.model.Scene;
public interface SceneService{

    int insert(Scene scene);

    int insertSelective(Scene scene);

    int insertList(List<Scene> scenes);

    int update(Scene scene);

    String  addScene(Scene scene);

    String findSceneByOtherId(Scene scene);

    String deleteScene(Scene scene);


}
