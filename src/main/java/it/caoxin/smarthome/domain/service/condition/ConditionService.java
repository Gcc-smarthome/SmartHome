package it.caoxin.smarthome.domain.service.condition;

import java.util.List;
import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.model.Scene;

public interface ConditionService{

    int insert(Condition condition);

    int insertSelective(Condition condition);

    int insertList(List<Condition> conditions);

    int update(Condition condition);

    List<Condition> getConditionsBySceneId(Scene scene);

    int deleteConditionById(Condition condition);

    String addCondition(Condition condition);
}
