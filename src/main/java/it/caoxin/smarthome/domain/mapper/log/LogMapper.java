package it.caoxin.smarthome.domain.mapper.log;


import it.caoxin.smarthome.domain.model.Log;

/**
*@Author:Caoxin
*@Description
*@Date:20:47 2018/1/31
*@param
*@return
*/
public interface LogMapper {
    int deleteById(Integer id);

    int insert(Log log);

    int insertSelective(Log log);

    Log selectById(Integer id);

    int updateByIdSelective(Log log);

    int updateById(Log log);
}