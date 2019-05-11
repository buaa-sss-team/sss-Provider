package com.yuyuyzl.SSS.impl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yuyuyzl.SSS.IDBService;
import com.yuyuyzl.SSS.models.user;
import com.yuyuyzl.SSS.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service("DBService")
public class DBServiceImpl implements IDBService{
    private Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);
    //添加用户
    public Boolean addUser(user x) {
        logger.info("添加用户[id:{}, name:{}, passwd:{}]",x.getId(),x.getName(),x.getPassword());
        Connection conn=DBUtil.getConnection();
        String sql="INSERT INTO user(id,name,password,personalInformationJson,boughtThings,credit)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1,x.getId());
            ptmt.setString(2,x.getName());
            ptmt.setString(3,x.getPassword());
            ptmt.setString(4,x.getPersonalInformationJson());
            ptmt.setString(5,x.getBoughtThings());
            ptmt.setInt(6,x.getCredit());
            ptmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.info("添加用户失败 用户:[name:{}]",x.getName());
            return false;
        }
        logger.info("添加用户成功 用户:[name:{}]",x.getName());
        return true;
    }
    //删除用户
    public Boolean delUser(int id) {
        logger.info("删除用户 [id:{}]",id);
        Connection conn=DBUtil.getConnection();
        String sql="DELETE FROM user WHERE id=?";
        try{
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setInt(1,id);
            ptmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.info("删除用户失败 用户:[id:{}]",id);
            return false;
        }
        logger.info("删除用户成功 用户:[id:{}]",id);
        return true;
    }
    //修改用户
    public Boolean updateUser(user x) {
        logger.info("修改用户 [id:{}]",x.getId());
        Connection conn=DBUtil.getConnection();
        String sql="UPDATE user " +
                "SET name=?,password=?,personalInformationJson=?,boughtThings=?,credit=? "+
                "WHERE id=?";
        try{
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setString(1,x.getName());
            ptmt.setString(2,x.getPassword());
            ptmt.setString(3,x.getPersonalInformationJson());
            ptmt.setString(4,x.getBoughtThings());
            ptmt.setInt(5,x.getCredit());
            ptmt.setInt(6,x.getId());
            ptmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.info("修改用户失败 用户:[id:{}]",x.getId());
            return false;
        }
        logger.info("修改用户成功 用户:[id:{}]",x.getId());
        return true;
    }

    public user queryUser(int id) {
        logger.info("查询用户 [id:{}]",id);
        Connection conn=DBUtil.getConnection();
        String sql="SELECT * FROM user WHERE id=?";
        try{
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setInt(1,id);
            ResultSet res=ptmt.executeQuery();
            if(!res.next()){//不存在用户
                throw new SQLException();
            }
            user ret=new user();
            ret.setId(res.getInt("id"));
            ret.setName(res.getString("name"));
            ret.setPassword(res.getString("password"));
            ret.setPersonalInformationJson(res.getString("personalInformationJson"));
            ret.setBoughtThings(res.getString("boughtThings"));
            ret.setCredit(res.getInt("credit"));
            return ret;
        }
        catch(SQLException e){
            e.printStackTrace();;
            logger.info("不存在该id的用户 [id:{}]",id);
            return null;
        }
    }
}