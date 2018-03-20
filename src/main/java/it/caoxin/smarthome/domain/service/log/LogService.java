package it.caoxin.smarthome.domain.service.log;

import it.caoxin.smarthome.domain.model.Log;

public interface LogService {
    int deleteById(Integer id);

    int insert(Log log);

    int insertSelective(Log log);

    Log selectById(Integer id);

    int updateByIdSelective(Log log);

    int updateById(Log log);
}
