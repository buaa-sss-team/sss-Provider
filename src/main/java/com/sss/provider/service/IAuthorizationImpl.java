package com.sss.provider.service;

import com.sss.interfaces.dao.IHDBdao;
import com.sss.interfaces.hmodel.User;
import com.sss.provider.dao.HDBdao;
import com.sss.provider.util.impl.DBService;
import com.sss.interfaces.model.user;
import com.sss.interfaces.service.IAuthorization;
import com.sss.provider.util.impl.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("IAuthorization")
@Transactional
public class IAuthorizationImpl implements IAuthorization {
    /*@Autowired
    private HDBdao hdbdao;*/

    private String msg;
    private Logger logger = LoggerFactory.getLogger(IAuthorizationImpl.class);

    public int userLogin(String name, String pwd) {
        //加载hdbdao
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        IHDBdao hdbdao=ctx.getBean(IHDBdao.class);
        try{
                List<User> userList=hdbdao.getUserByName(name);
                logger.info("size"+userList.size());
                logger.info("用户登录");
                for(User user:userList){
                    //logger.info("用户名：[username:{}, 密码：password:{}]", user.getName(), user.getPassword());
                    logger.info("username:"+user.getName()+ "pwd:"+user.getPassword());
                    if(user!=null && user.getPassword().equals( pwd )){
                        User x=new User();
                        x.setName(name);
                        x.setBoughtThings("");
                        x.setCredit(40);
                        x.setPassword(pwd);
                        x.setPersonalInformationJson("");
                        hdbdao.insert(x);
                        logger.info("用户登录成功");
                        return 1;
                    }
                    logger.info("用户登陆失败");
                }
        }catch(NullPointerException e){
            e.printStackTrace();
            logger.info("exception");
        }
        return 0;
    }

    public int userSignIn(String name, String pwd) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        IHDBdao hdbdao=ctx.getBean(IHDBdao.class);
        User x=new User();
        x.setName(name);
        x.setBoughtThings("");
        x.setCredit(40);
        x.setPassword(pwd);
        x.setPersonalInformationJson("");
        hdbdao.insert(x);
        System.out.println("注册成功！！！！！！！"+hdbdao);
        return 1;
    }
}
