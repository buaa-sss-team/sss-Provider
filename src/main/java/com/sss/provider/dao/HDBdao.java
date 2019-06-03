package com.sss.provider.dao;

import com.alibaba.dubbo.cache.CacheFactory;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.sss.interfaces.dao.IHDBdao;
import net.sf.ehcache.hibernate.EhcacheJtaTransactionManagerLookup;
import org.hibernate.Query;
import org.hibernate.cache.EhCacheProvider;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sss.interfaces.hmodel.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
@Service("HDBdao")
public class HDBdao implements IHDBdao{
    private static Configuration config=null;
    private static SessionFactory sessionFactory=null;
    private Logger logger = LoggerFactory.getLogger(HDBdao.class);
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

            //CacheManager cm=new CacheManager();
            //cm.clearAll();
            ;
            session.delete(obj);

            //cm.getEhcache("sampleCache1");

            //session.flush();
            //obj=null;
            //((User)obj).setId(-100);
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

    public User getUserByName(String name){
        logger.info("intogetuserbyname");
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        User ret=null;
        try {
            Query query=session.createQuery("FROM User WHERE account=?");
            ((Query) query).setParameter(0,name);
            List<User> tmp=query.list();
            if(tmp.size()==0)return null;
            ret=tmp.get(0);
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


    public List<Tobeexpert> getTobeexpertBystatus(int st){
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        List<Tobeexpert> ret=null;
        try{
            Query query=session.createQuery("FROM Tobeexpert WHERE status=?");
            query.setParameter(0,st);
            ret=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        session.close();
        return ret;
    }

    public List<Buyres> getBuyresBystatus(int st){
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        List<Buyres> ret=null;
        try{
            Query query=session.createQuery("FROM Buyres WHERE getstatus=?");
            query.setParameter(0,st);
            ret=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        session.close();
        return ret;
    }

    public List<Tradeinfo> getTradeInfoByUserId(int userid){
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        List<Tradeinfo> ret=null;
        try{
            Query query=session.createQuery("FROM Tradeinfo WHERE userid=?");
            query.setParameter(0,userid);
            ret=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
            if(tx != null && tx.isActive())
                tx.rollback();
        }
        session.close();
        return ret;
    }
}