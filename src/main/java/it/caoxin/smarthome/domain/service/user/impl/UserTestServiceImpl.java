package it.caoxin.smarthome.domain.service.user.impl;

import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.base.impl.BaseService;
import it.caoxin.smarthome.domain.service.user.UserTestService;
import org.springframework.stereotype.Service;

@Service("userTestService")
public class UserTestServiceImpl extends BaseService<User> implements UserTestService<User> {

}
