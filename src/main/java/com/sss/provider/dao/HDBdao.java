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
    /*
        通用插入
        返回0 插入成功
        返回1 插入失败
     */
    public int insert(Object obj){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        int ret=0;
        try {
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch(Exception e){
            ret=1;
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        return ret;
    }
    /*
        通用更新
        返回0 更新成功
        返回1 更新失败
     */
    public int update(Object obj){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        int ret=0;
        try {
            session.update(obj);
            tx.commit();
        }
        catch(Exception e){
            ret=1;
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        return ret;
    }
    /*
            通用删除
            返回0 删除成功
            返回1 删除失败
    */
    public int delete(Object obj){
        Session session=sessionFactory.getCurrentSession();
        Transaction tx=session.beginTransaction();
        int ret=0;
        try {
            session.delete(obj);
            tx.commit();
        }
        catch(Exception e){
            ret=1;
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        return ret;
    }
    public Object getByID(Class cls,int id){
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        Object ret=null;
        try{
            ret=session.get(cls,id);
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        finally {
            session.close();
            return ret;
        }
    }

    public List<User> getUserByName(String name){
        Session session=sessionFactory.openSession();
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
            session.close();
            return ret;
        }
    }

}
