package it.caoxin.smarthome.domain.service.condition;

import java.util.List;
import it.caoxin.smarthome.domain.model.Condition;
public interface ConditionService{

    int insert(Condition condition);

    int insertSelective(Condition condition);

    int insertList(List<Condition> conditions);

    int update(Condition condition);
}
