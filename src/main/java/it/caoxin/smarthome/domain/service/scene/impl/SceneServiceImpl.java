package it.caoxin.smarthome.domain.service.scene.impl;

import it.caoxin.smarthome.domain.mapper.condition.ConditionMapper;
import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.model.Scene;
import it.caoxin.smarthome.domain.service.condition.ConditionService;
import it.caoxin.smarthome.domain.service.scene.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import it.caoxin.smarthome.domain.mapper.scene.SceneMapper;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private ConditionService conditionService;

    @Override
    public int insert(Scene scene){
        return sceneMapper.insert(scene);
    }

    @Override
    public int insertSelective(Scene scene){
        return sceneMapper.insertSelective(scene);
    }

    @Override
    public int insertList(List<Scene> scenes){
        return sceneMapper.insertList(scenes);
    }

    @Override
    public int update(Scene scene){
        return sceneMapper.update(scene);
    }

    //添加场景
    @Override
    public String addScene(Scene scene) {
        if (scene.getUserId() != null && scene.getDeviceId() != null && scene.getFamilyId() != null){
            insertSelective(scene);
            return "add scene success";
        }
        return "add scene failure";
    }

    //通过userid，deviceId，familyId查询场景
    @Override
    public String findSceneByOtherId(Scene scene) {
        if (scene.getUserId() != null && scene.getDeviceId() != null && scene.getFamilyId() != null) {
            Scene selectScene = sceneMapper.selectByUserIdAndFamilyIdAndDeviceId(
                    scene.getFamilyId(),
                    scene.getDeviceId(),
                    scene.getUserId());
            return "find scene success";
        }
        return "find scene failure";
    }

    @Override
    public String deleteScene(Scene scene) {
        //首先删除所有的条件
        if(scene.getId() != null){
            List<Condition> conditions =
                    conditionService.getConditionsBySceneId(scene);

            for (Condition condition : conditions){
                conditionService.deleteConditionById(condition);
            }

            sceneMapper.deleteSceneById(scene.getId());

            return "delete success";
        }
        return "delete failure";
    }


}
