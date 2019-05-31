package com.sss.provider.dao;
import com.sss.interfaces.dao.IHDBdao;

import com.sss.interfaces.hmodel.User;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("HDBdao")
public class HDBdao implements IHDBdao{
    private static Configuration config=null;
    private static SessionFactory sessionFactory=null;

    HDBdao(){
        config=new Configuration().configure();
        sessionFactory=config.buildSessionFactory();
    }
    //插入User
    public void insertUser(User who){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        try {
            session.saveOrUpdate(who);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            if(session!=null&&session.isOpen())
                session.close();
        }
    }
    //更新User
    public void updateUser(User who){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        try {
            session.update(who);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            if(session!=null&&session.isOpen())
                session.close();
        }
    }
    //返回该id的User
    public User getUserByID(int id){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        User ret=null;
        try {
            ret = (User) session.get(User.class, id);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            if(session!=null&&session.isOpen())
                session.close();
            return ret;
        }
    }



    //删除该ID的User
    public void deleteUserByID(int ID){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        User who=(User)session.get(User.class,new Integer(ID));
        try{
            session.delete(who);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            if(session!=null&&session.isOpen())
                session.close();
        }
    }

    public List<User> getUserByName(String name){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        List<User> ret=null;
        try {
            Query query=session.createQuery("FROM User WHERE name=?");
            ((Query) query).setParameter(0,name);
            ret=query.list();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            if(session!=null&&session.isOpen())
                session.close();
            return ret;
        }
    }

}
