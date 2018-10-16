package it.caoxin.smarthome.domain.service.base.impl;

import it.caoxin.smarthome.domain.mapper.base.IBaseMapper;
import it.caoxin.smarthome.domain.mapper.user.UserTestMapper;
import it.caoxin.smarthome.domain.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;

public class BaseService<T> implements IBaseService<T>{
    @Autowired
    private ApplicationContext context;
    @Override
    public IBaseMapper getMapper() {
        Class clazz;
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        String simpleName = clazz.getSimpleName();
        String s = simpleName.toLowerCase();
        char[] chars = s.toCharArray();
        char aChar = chars[0];

        String newName = String.valueOf(aChar) + simpleName.substring(1,simpleName.length());
        System.out.println(newName);

        IBaseMapper userTestMapper = (IBaseMapper) context.getBean(newName + "TestMapper");

        return userTestMapper;
    }

    @Override
    public int deleteById(Integer id) {
        return getMapper().deleteById(id);
    }

    @Override
    public int insert(T device) {
        return 0;
    }

    @Override
    public int insertSelective(T device) {
        return 0;
    }

    @Override
    public T selectById(T id) {
        return null;
    }

    @Override
    public int updateByIdSelective(T device) {
        return 0;
    }

    @Override
    public int updateById(T device) {
        return 0;
    }
}
