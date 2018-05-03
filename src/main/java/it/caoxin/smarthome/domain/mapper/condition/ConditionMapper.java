package it.caoxin.smarthome.domain.mapper.condition;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import it.caoxin.smarthome.domain.model.Condition;

@Mapper
public interface ConditionMapper {
    int insert(@Param("condition") Condition condition);

    int insertSelective(@Param("condition") Condition condition);

    int insertList(@Param("conditions") List<Condition> conditions);

    int update(@Param("condition") Condition condition);
}
