package it.caoxin.smarthome.domain.service.base;


import it.caoxin.smarthome.domain.mapper.base.IBaseMapper;

public interface IBaseService<T> {

    IBaseMapper getMapper();

    int deleteById(Integer id);

    int insert(T device);

    int insertSelective(T device);

    T selectById(T id);

    int updateByIdSelective(T device);

    int updateById(T device);
}
