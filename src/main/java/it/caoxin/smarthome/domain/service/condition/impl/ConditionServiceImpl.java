package it.caoxin.smarthome.domain.service.condition.impl;

import it.caoxin.smarthome.domain.mapper.condition.ConditionMapper;
import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.model.Scene;
import it.caoxin.smarthome.domain.service.condition.ConditionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Resource
    private ConditionMapper conditionMapper;

    @Override
    public int insert(Condition condition){
        return conditionMapper.insert(condition);
    }

    @Override
    public int insertSelective(Condition condition){
        return conditionMapper.insertSelective(condition);
    }

    @Override
    public int insertList(List<Condition> conditions){
        return conditionMapper.insertList(conditions);
    }

    @Override
    public int update(Condition condition){
        return conditionMapper.update(condition);
    }

    @Override
    public List<Condition> getConditionsBySceneId(Scene scene) {
        return conditionMapper.selectScenesById(scene.getId());
    }

    @Override
    public int deleteConditionById(Condition Condition) {
        return conditionMapper.deleteById(Condition.getId());
    }

    @Override
    public String addCondition(Condition condition) {
        if(condition.getSceneId() != null){
            insertSelective(condition);
            return "add condition success";
        }
        return "add condition failure";
    }
}
