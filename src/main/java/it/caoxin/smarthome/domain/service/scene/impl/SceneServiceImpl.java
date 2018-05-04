package it.caoxin.smarthome.domain.service.scene.impl;

import it.caoxin.smarthome.domain.mapper.condition.ConditionMapper;
import it.caoxin.smarthome.domain.mapper.device.DeviceMapper;
import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.model.Device;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.Scene;
import it.caoxin.smarthome.domain.service.condition.ConditionService;
import it.caoxin.smarthome.domain.service.device.DeviceService;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import it.caoxin.smarthome.domain.service.scene.SceneService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.caoxin.smarthome.domain.mapper.scene.SceneMapper;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private ConditionService conditionService;

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private DeviceMapper deviceMapper;

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
    public String findSceneById(Scene scene) {
        Map valueStack = new HashMap<String,Object>();
        if (scene.getId() != null) {
            Scene selectScene = sceneMapper.selectSceneById(scene.getId());
            valueStack.put("scene",selectScene);

            System.out.println("familyId:"+selectScene.getFamilyId());
            Family family = familyMapper.selectById(selectScene.getFamilyId());
            valueStack.put("family",family);
            System.out.println("deviceId:"+selectScene.getDeviceId());
            for (Device device : family.getDeviceList()){
                if(device.getId().equals(selectScene.getDeviceId())){
                    System.out.println("id");
                    valueStack.put("device",device);
                }
            }

            List<Condition> conditions = conditionService.getConditionsBySceneId(selectScene);
            valueStack.put("condition",conditions);


            return JSONArray.fromObject(valueStack).toString();
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

    //通过用户id查询该用户设置的所有场景
    @Override
    public String findSceneByUserId(Integer userId) {
        if (userId != null) {
            List<Scene> scenes = sceneMapper.selectSceneByUserId(userId);
            return JSONArray.fromObject(scenes).toString();
        }
        return "get scens failure";
    }


}
