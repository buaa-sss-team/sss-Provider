package com.sss.provider.service;

import com.sss.interfaces.ITestUserDao;
import com.sss.interfaces.model.TestUser;
import com.sss.interfaces.ITestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service 把当前类在spring中注册为一个服务组件，spring实例化对象时可以根据配置执行依赖注入
//@com.alibaba.dubbo.config.annotation.Service
@Service("TestUserService")
@Transactional
public class TestUserService implements ITestUserService {

    @Autowired
    private ITestUserDao ud;

    public TestUser getUser(int id){
        return ud.getUser(id);
    }
    public List<TestUser> getAllUser(){
        List<TestUser> list = ud.getAllUser();
        return list;
    }
    public boolean addUser(TestUser user){
        if (ud.addUser(user))
            return true;
        else return false;
    }
}
