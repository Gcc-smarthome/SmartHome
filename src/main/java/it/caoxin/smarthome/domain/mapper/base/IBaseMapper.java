package it.caoxin.smarthome.domain.mapper.base;


public interface IBaseMapper<T> {
    int deleteById(Integer id);

    int insert(T t);

    int insertSelective(T t);

    T selectById(Integer id);

    int updateByIdSelective(T t);

    int updateById(T t);
}
