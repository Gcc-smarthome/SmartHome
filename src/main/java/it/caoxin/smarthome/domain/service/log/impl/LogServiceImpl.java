package it.caoxin.smarthome.domain.service.log.impl;

import it.caoxin.smarthome.domain.mapper.log.LogMapper;
import it.caoxin.smarthome.domain.model.Log;
import it.caoxin.smarthome.domain.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public int deleteById(Integer id) {
        return logMapper.deleteById(id);
    }

    @Override
    public int insert(Log log) {
        return logMapper.insert(log);
    }

    @Override
    public int insertSelective(Log log) {
        return logMapper.insertSelective(log);
    }

    @Override
    public Log selectById(Integer id) {
        return logMapper.selectById(id);
    }

    @Override
    public int updateByIdSelective(Log log) {
        return logMapper.updateByIdSelective(log);
    }

    @Override
    public int updateById(Log log) {
        return logMapper.updateById(log);
    }
}
