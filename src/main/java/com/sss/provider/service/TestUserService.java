package com.sss.provider.service;

import com.sss.interfaces.ITestUserService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service 把当前类在spring中注册为一个服务组件，spring实例化对象时可以根据配置执行依赖注入
//@com.alibaba.dubbo.config.annotation.Service
@Service("TestUserService")
@Transactional
public class TestUserService implements ITestUserService {

    @Autowired
    private HDBdao hdBdao;

    public User getUser(int id){
        return (User) hdBdao.getByID(User.class,id);
    }

    public boolean addUser(User user){
        if (hdBdao.insert(user) == 0)
            return true;
        else return false;
    }
}
