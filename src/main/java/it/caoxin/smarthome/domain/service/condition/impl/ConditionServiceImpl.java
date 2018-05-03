package it.caoxin.smarthome.domain.service.condition.impl;

import it.caoxin.smarthome.domain.mapper.condition.ConditionMapper;
import it.caoxin.smarthome.domain.model.Condition;
import it.caoxin.smarthome.domain.service.condition.ConditionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Resource
    private ConditionMapper conditionDao;

    @Override
    public int insert(Condition condition){
        return conditionDao.insert(condition);
    }

    @Override
    public int insertSelective(Condition condition){
        return conditionDao.insertSelective(condition);
    }

    @Override
    public int insertList(List<Condition> conditions){
        return conditionDao.insertList(conditions);
    }

    @Override
    public int update(Condition condition){
        return conditionDao.update(condition);
    }
}
