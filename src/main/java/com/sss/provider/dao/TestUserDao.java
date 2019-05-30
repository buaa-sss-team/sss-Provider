package com.sss.provider.dao;

import com.sss.interfaces.ITestUserDao;
import com.sss.interfaces.model.TestUser;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestUserDao implements ITestUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public TestUser getUser(int id){
        TestUser user =(TestUser) sessionFactory.getCurrentSession().get(TestUser.class,id);
        return user;
    }

    public List<TestUser> getAllUser(){
        String hql = "from testuser";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public boolean addUser(TestUser user){
        sessionFactory.getCurrentSession().save(user);
        return true;
    }
}
