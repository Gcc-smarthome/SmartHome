package it.caoxin.smarthome.domain.service.scene.impl;

import it.caoxin.smarthome.domain.model.Scene;
import it.caoxin.smarthome.domain.service.scene.SceneService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import it.caoxin.smarthome.domain.mapper.scene.SceneMapper;

@Service
public class SceneServiceImpl implements SceneService {

    @Resource
    private SceneMapper sceneMapper;

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
}
