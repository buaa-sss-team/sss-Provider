package com.sss.provider.util.impl;
import com.sss.interfaces.IDBService;
import com.sss.interfaces.model.*;
import com.sss.provider.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service("DBService")
public class DBService implements IDBService {
    private Logger logger = LoggerFactory.getLogger(DBService.class);

    //添加用户
    public Boolean addUser(user x) {
        logger.info("添加用户[id:{}, name:{}, passwd:{}]", x.getId(), x.getName(), x.getPassword());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO user(id,name,password,personalInformationJson,boughtThings,credit)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getId());
            ptmt.setString(2, x.getName());
            ptmt.setString(3, x.getPassword());
            ptmt.setString(4, x.getPersonalInformationJson());
            ptmt.setString(5, x.getBoughtThings());
            ptmt.setInt(6, x.getCredit());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加用户失败 用户:[name:{}]", x.getName());
            return false;
        }
        logger.info("添加用户成功 用户:[name:{}]", x.getName());
        return true;
    }

    //删除用户
    public Boolean delUser(int id) {
        logger.info("删除用户 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM user WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除用户失败 用户:[id:{}]", id);
            return false;
        }
        logger.info("删除用户成功 用户:[id:{}]", id);
        return true;
    }

    //修改用户
    public Boolean updateUser(user x) {
        logger.info("修改用户 [id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE user " +
                "SET name=?,password=?,personalInformationJson=?,boughtThings=?,credit=? " +
                "WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getName());
            ptmt.setString(2, x.getPassword());
            ptmt.setString(3, x.getPersonalInformationJson());
            ptmt.setString(4, x.getBoughtThings());
            ptmt.setInt(5, x.getCredit());
            ptmt.setInt(6, x.getId());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改用户失败 用户:[id:{}]", x.getId());
            return false;
        }
        logger.info("修改用户成功 用户:[id:{}]", x.getId());
        return true;
    }

    //查找用户
    public user queryUser(int id) {
        logger.info("查询用户 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM user WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            user ret = new user();
            ret.setId(res.getInt("id"));
            ret.setName(res.getString("name"));
            ret.setPassword(res.getString("password"));
            ret.setPersonalInformationJson(res.getString("personalInformationJson"));
            ret.setBoughtThings(res.getString("boughtThings"));
            ret.setCredit(res.getInt("credit"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            logger.info("不存在该id的用户 [id:{}]", id);
            return null;
        }
    }

    //增加专家
    public Boolean addExpert(expert x) {
        logger.info("添加专家[id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO expert(id,achievements,profession,searchPopularity)VALUES(?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getId());
            ptmt.setString(2, x.getAchievement());
            ptmt.setString(3, x.getProfession());
            ptmt.setFloat(4, x.getSearchPopularity());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加专家失败 专家:[id:{}]", x.getId());
            return false;
        }
        logger.info("添加专家成功 专家:[id:{}]", x.getId());
        return true;
    }

    //删除专家
    public Boolean delExpert(int id) {
        logger.info("删除专家[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM expert WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除专家失败 专家:[id:{}]", id);
            return false;
        }
        logger.info("删除专家成功 专家:[id:{}]", id);
        return true;
    }

    //修改专家
    public Boolean updateExpert(expert x) {
        logger.info("修改专家 [id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE expert " +
                "SET achievements=?,profession=?,searchPopularity=? " +
                "WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getAchievement());
            ptmt.setString(2, x.getProfession());
            ptmt.setFloat(3, x.getSearchPopularity());
            ptmt.setInt(4, x.getId());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改专家失败 专家:[id:{}]", x.getId());
            return false;
        }
        logger.info("修改专家成功 专家:[id:{}]", x.getId());
        return true;
    }
    //查找专家

    public expert queryExpert(int id) {
        logger.info("查询专家 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM expert WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            expert ret = new expert();
            ret.setId(res.getInt("id"));
            ret.setAchievement(res.getString("achievements"));
            ret.setProfession(res.getString("profession"));
            ret.setSearchPopularity(res.getFloat("searchPopularity"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            logger.info("不存在该id的专家 [id:{}]", id);
            return null;
        }
    }

    //添加管理员
    public Boolean addAdministrator(administrator x) {
        logger.info("添加管理员[id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO administrator(id,name,password,personalInformationJson)VALUES(?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getId());
            ptmt.setString(2, x.getName());
            ptmt.setString(3, x.getPassword());
            ptmt.setString(4, x.getPersonalInformationJson());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加管理员失败 管理员:[name:{}]", x.getName());
            return false;
        }
        logger.info("添加管理员成功 管理员:[name:{}]", x.getName());
        return true;
    }

    //删除管理员
    public Boolean delAdministrator(int id) {
        logger.info("删除管理员[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM administrator WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除管理员失败 管理员:[id:{}]", id);
            return false;
        }
        logger.info("删除管理员成功 管理员:[id:{}]", id);
        return true;
    }

    //查找管理员
    public administrator queryAdministrator(int id) {
        logger.info("查询管理员 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM administrator WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            administrator ret = new administrator();

            ret.setId(res.getInt("id"));
            ret.setName(res.getString("name"));
            ret.setPassword(res.getString("password"));
            ret.setPersonalInformationJson(res.getString("personalInformationJson"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            logger.info("不存在该id的管理员 [id:{}]", id);
            return null;
        }
    }

    //更新管理员
    public Boolean updateAdministrator(administrator x) {
        logger.info("修改管理员 [id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE administrator " +
                "SET name=?,password=?,personalInformationJson=? " +
                "WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getName());
            ptmt.setString(2, x.getPassword());
            ptmt.setString(3, x.getPersonalInformationJson());
            ptmt.setInt(4, x.getId());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改管理员失败 管理员:[id:{}]", x.getId());
            return false;
        }
        logger.info("修改管理员成功 管理员:[id:{}]", x.getId());
        return true;
    }

    //科技文献
    //添加科技文献
    public Boolean addScientificRes(scientificRes x) {
        logger.info("添加科技文献[id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO scientificRes(authorsID,resID, resTitle, searchPopularity, downloadPopularity, organization, abstract, url)VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setString(1, x.getAuthorsID());
            ptmt.setInt(2, x.getResID());
            ptmt.setString(3, x.getResTitle());
            ptmt.setFloat(4, x.getSearchPopularity());
            ptmt.setFloat(5, x.getDownloadPopularity());
            ptmt.setString(6, x.getOrganization());
            ptmt.setString(7, x.getAbstracts());
            ptmt.setString(8, x.getUrl());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加科技资源失败 资源:[id:{}]", x.getResID());
            return false;
        }
        logger.info("添加科技资源成功 资源:[id:{}]", x.getResID());
        return true;
    }

    //删除科技资源
    public Boolean delScientificRes(int id) {
        logger.info("删除科技资源[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM scientificRes WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除科技资源失败 资源:[id:{}]", id);
            return false;
        }
        logger.info("删除科技资源成功 资源:[id:{}]", id);
        return true;
    }

    //查找科技资源
    public scientificRes queryScientificRes(int id) {
        logger.info("查询科技资源 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM scientificres WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            scientificRes ret = new scientificRes();

            ret.setAuthorsID(res.getString("authorsID"));
            ret.setResID(res.getInt("resID"));
            ret.setResTitle(res.getString("resTitle"));
            ret.setSearchPopularity(res.getFloat("searchPopularity"));
            ret.setDownloadPopularity(res.getFloat("downloadPopularity"));
            ret.setOrganization(res.getString("organization"));
            ret.setAbstracts(res.getString("abstract"));
            ret.setUrl(res.getString("url"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            logger.info("不存在该id的科技资源 [id:{}]", id);
            return null;
        }
    }

    //更新科技资源
    public Boolean updateScientificRes(scientificRes x) {
        logger.info("修改科技资源 [id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE scientificres " +
                "SET authorsID=?,resTitle=?, searchPopularity=?, downloadPopularity=?, organization=?, abstract=?, url=? " +
                "WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getAuthorsID());
            ptmt.setString(2, x.getResTitle());
            ptmt.setFloat(3, x.getSearchPopularity());
            ptmt.setFloat(4, x.getDownloadPopularity());
            ptmt.setString(5, x.getOrganization());
            ptmt.setString(6, x.getAbstracts());
            ptmt.setString(7, x.getUrl());
            ptmt.setInt(8, x.getResID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改管理员失败 资源:[id:{}]", x.getResID());
            return false;
        }
        logger.info("修改科技资源成功 资源:[id:{}]", x.getResID());
        return true;
    }

    //增加专利
    public Boolean addPatent(patent x) {
        logger.info("添加专利[id:{}]", x.getPatentID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO patent(resID, patentID, patentType)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getResID());
            ptmt.setInt(2, x.getPatentID());
            ptmt.setString(3, x.getPatentType());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加专利失败 专利:[id:{}]", x.getPatentID());
            return false;
        }
        logger.info("添加专利成功 专利:[id:{}]", x.getPatentID());
        return true;
    }

    //删除专利
    public Boolean delPatent(int id) {
        logger.info("删除专利[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM patent WHERE patentID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除专利失败 专利:[id:{}]", id);
            return false;
        }
        logger.info("删除专利成功 专利:[id:{}]", id);
        return true;
    }

    //修改专利
    public Boolean updatePatent(patent x) {
        logger.info("修改专利 [id:{}]", x.getPatentID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE patent " +
                "SET resID=?,patentType =?" +
                "WHERE patentID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getResID());
            ptmt.setString(2, x.getPatentType());
            ptmt.setInt(3, x.getPatentID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改专利失败 专利:[id:{}]", x.getPatentID());
            return false;
        }
        logger.info("修改专利成功 专利:[id:{}]", x.getPatentID());
        return true;
    }
    //查找专利

    public patent queryPatent(int id) {
        logger.info("查询专利 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM patent WHERE patentID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            patent ret = new patent();
            ret.setResID(res.getInt("resID"));
            ret.setPatentID(res.getInt("patentID"));
            ret.setPatentType(res.getString("patentType"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            logger.info("不存在该id的专利 [id:{}]", id);
            return null;
        }
    }


    //增加论文
    public Boolean addPaper(paper x) {
        logger.info("添加论文[id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO paper(resID,source,keyWords)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getResID());
            ptmt.setString(2, x.getSource());
            ptmt.setString(3, x.getKeyWords());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加论文失败 论文:[id:{}]", x.getResID());
            return false;
        }
        logger.info("添加论文成功 论文:[id:{}]", x.getResID());
        return true;
    }

    //删除论文
    public Boolean delPaper(int id) {
        logger.info("删除论文[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM paper WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除论文失败 论文:[id:{}]", id);
            return false;
        }
        logger.info("删除论文成功 论文:[id:{}]", id);
        return true;
    }

    //修改论文
    public Boolean updatePaper(paper x) {
        logger.info("修改论文 [id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE paper " +
                "SET source=?,keyWords =?" +
                "WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getSource());
            ptmt.setString(2, x.getKeyWords());
            ptmt.setInt(3, x.getResID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改论文失败 论文:[id:{}]", x.getResID());
            return false;
        }
        logger.info("修改论文成功 论文:[id:{}]", x.getResID());
        return true;
    }
    //查找论文

    public paper queryPaper(int id) {
        logger.info("查询论文 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM paper WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            paper ret = new paper();
            ret.setResID(res.getInt("resID"));
            ret.setSource(res.getString("source"));
            ret.setKeyWords(res.getString("keyWords"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的论文 [id:{}]", id);
            return null;
        }
    }
    //审核信息表

    //添加审核信息

    public Boolean addAuditapplicayion(auditapplication x) {
        logger.info("添加审核信息表[id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO auditapplication(auditID,customID,adminID,createTime,status)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getAuditID());
            ptmt.setInt(2, x.getCustomID());
            ptmt.setInt(3, x.getAdminID());
            ptmt.setTime(4, x.getCreatetime());
            ptmt.setInt(5, x.getStatus());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加审核信息失败 审核信息:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("添加审核信息成功 审核信息:[id:{}]", x.getAuditID());
        return true;
    }
    //删除审核信息


    public Boolean delAuditapplication(int id) {
        logger.info("删除审核信息[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM auditapplication WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除审核信息失败 审核信息:[id:{}]", id);
            return false;
        }
        logger.info("删除审核信息成功 审核信息:[id:{}]", id);
        return true;
    }
    //修改审核信息

    public Boolean updateAuditapplication(auditapplication x) {
        logger.info("修改审核信息 [id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE auditapplication " +
                "SET customID=?,adminID=?,createTime=?,`status`=?" +
                "WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getCustomID());
            ptmt.setInt(2, x.getAdminID());
            ptmt.setTime(3, x.getCreatetime());
            ptmt.setInt(4, x.getStatus());
            ptmt.setInt(5,x.getAuditID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改审核信息失败 审核信息:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("修改审核信息成功 审核信息:[id:{}]", x.getAuditID());
        return true;
    }
    //查询审核信息


    public auditapplication queryAuditapplication(int id) {
        logger.info("查询审核信息 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM auditapplication WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            auditapplication ret = new auditapplication();
            ret.setAuditID(res.getInt("auditID"));
            ret.setCustomID(res.getInt("customID"));
            ret.setAdminID(res.getInt("adminID"));
            ret.setCreatetime(res.getTime("createtime"));
            ret.setStatus(res.getInt("status"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的审核信息 [id:{}]", id);
            return null;
        }
    }


    //入驻申请
    //增加入驻申请
    public Boolean addSettleIn(settleIn x) {
        logger.info("添加入住申请[id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO settlein(auditID,content)VALUES(?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getAuditID());
            ptmt.setString(2, x.getContent());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加入驻申请失败 入驻申请:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("添加入驻申请成功 入驻申请:[id:{}]", x.getAuditID());
        return true;
    }
    //删除入驻申请
    public Boolean delSettleIn(int id) {
        logger.info("删除入驻申请[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM settlein WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除入驻申请失败 入驻申请:[id:{}]", id);
            return false;
        }
        logger.info("删除入驻申请 入驻申请:[id:{}]", id);
        return true;
    }
//修改入驻申请

    public Boolean updateSettleIn(settleIn x) {
        logger.info("修改入驻申请 [id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE settlein " +
                "SET content=?" +
                "WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getContent());
            ptmt.setInt(2, x.getAuditID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改入驻申请失败 入驻申请:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("修改入驻申请成功 入驻申请:[id:{}]", x.getAuditID());
        return true;
    }
    //查询入驻申请


    public settleIn querySettleIn(int id) {
        logger.info("查询入驻申请 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM settlein WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            settleIn ret = new settleIn();
            ret.setAuditID(res.getInt("auditID"));
            ret.setContent(res.getString("content"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的入住申请 [id:{}]", id);
            return null;
        }
    }

    //购买科技资源
    //增加科技资源购买申请表
    public Boolean addBuyRes(buyRes x) {
        logger.info("添加科技资源购买申请[id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO buyres(auditID,type,costCredit)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getAuditID());
            ptmt.setInt(2, x.getType());
            ptmt.setInt(3,x.getCostCredit());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加科技资源购买申请失败 科技资源购买表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("添加科技资源购买申请成功 科技资源购买表:[id:{}]", x.getAuditID());
        return true;
    }
    //删除科技资源购买申请表
    public Boolean delBuyRes(int id) {
        logger.info("删除科技资源购买申请[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM buyres WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除科技资源购买申请失败 科技资源购买表:[id:{}]", id);
            return false;
        }
        logger.info("删除科技资源购买申请 科技资源购买表:[id:{}]", id);
        return true;
    }
//修改科技资源购买申请表

    public Boolean updateBuyRes(buyRes x) {
        logger.info("修改科技资源购买申请申请 [id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE buyres set `type`=?,`costCredit`=? WHERE `auditID`=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getType());
            ptmt.setInt(2,x.getCostCredit());
            ptmt.setInt(3, x.getAuditID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改科技资源购买申请失败 科技资源购买表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("修改科技资源购买申请成功 科技资源购买表:[id:{}]", x.getAuditID());
        return true;
    }
    //查询科技资源购买申请表


    public buyRes queryBuyRes(int id) {
        logger.info("查询科技资源购买申请 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM buyres WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            buyRes ret = new buyRes();
            ret.setAuditID(res.getInt("auditID"));
            ret.setType(res.getInt("type"));
            ret.setCostCredit(res.getInt("costCredit"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的科技资源购买申请 [id:{}]", id);
            return null;
        }
    }

    //现金业务
    //增加现金业务申请表
    public Boolean addPayment(payment x) {
        logger.info("添加现金业务申请[id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO payment(auditID,money,type)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getAuditID());
            ptmt.setDouble(2, x.getMoney());
            ptmt.setInt(3,x.getType());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加现金业务申请失败 现金业务表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("添加现金业务申请成功 现金业务表:[id:{}]", x.getAuditID());
        return true;
    }
    //删除现金业务申请表
    public Boolean delPayment(int id) {
        logger.info("删除现金业务申请[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM payment WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除现金业务申请失败 现金业务表:[id:{}]", id);
            return false;
        }
        logger.info("删除现金业务申请 现金业务表:[id:{}]", id);
        return true;
    }
//修改现金业务申请表

    public Boolean updatePayment(payment x) {
        logger.info("修改现金业务申请申请 [id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE payment " +
                "SET `money`=?,`type`=?" +
                "WHERE `auditID`=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setDouble(1, x.getMoney());
            ptmt.setInt(2,x.getType());
            ptmt.setInt(3, x.getAuditID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改现金业务申请失败 现金业务表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("修改现金业务申请成功 现金业务表:[id:{}]", x.getAuditID());
        return true;
    }
    //查询现金业务申请表


    public payment queryPayment(int id) {
        logger.info("查询现金业务申请 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM payment WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            payment ret = new payment();
            ret.setAuditID(res.getInt("auditID"));
            ret.setMoney(res.getInt("money"));
            ret.setType(res.getInt("type"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的现金业务申请 [id:{}]", id);
            return null;
        }
    }


    //修改科技资源
    //增加科技资源修改申请表
    public Boolean addModifySciRes(modifySciRes x) {
        logger.info("添加科技资源修改申请[id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO modifySciRes(auditID,type,itemID)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getAuditID());
            ptmt.setInt(2, x.getType());
            ptmt.setInt(3,x.getItemID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加科技资源修改申请失败 科技资源修改表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("添加科技资源修改申请成功 科技资源修改表:[id:{}]", x.getAuditID());
        return true;
    }
    //删除科技资源修改申请表
    public Boolean delModifySciRes(int id) {
        logger.info("删除科技资源修改申请[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM modifySciRes WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除科技资源修改申请失败 科技资源修改表:[id:{}]", id);
            return false;
        }
        logger.info("删除科技资源修改申请 科技资源修改表:[id:{}]", id);
        return true;
    }
//修改科技资源修改申请表

    public Boolean updateModifySciRes(modifySciRes x) {
        logger.info("修改科技资源修改申请申请 [id:{}]", x.getAuditID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE modifySciRes " +
                "SET `type`=?,`itemID`=?" +
                "WHERE `auditID`=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getType());
            ptmt.setInt(2,x.getItemID());
            ptmt.setInt(3, x.getAuditID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改科技资源修改申请失败 科技资源修改表:[id:{}]", x.getAuditID());
            return false;
        }
        logger.info("修改科技资源修改申请成功 科技资源修改表:[id:{}]", x.getAuditID());
        return true;
    }
    //查询科技资源修改申请表


    public modifySciRes queryModifySciRes(int id) {
        logger.info("查询科技资源修改申请 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM modifySciRes WHERE auditID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            modifySciRes ret = new modifySciRes();
            ret.setAuditID(res.getInt("auditID"));
            ret.setType(res.getInt("type"));
            ret.setItemID(res.getInt("itemID"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的科技资源修改申请 [id:{}]", id);
            return null;
        }
    }

    //用户购买科技资源表
    //增加用户购买科技资源表
    public Boolean addBoughtScientificRes(boughtScientificRes x) {
        logger.info("添加用户购买科技资源[id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO boughtScientificRes(id, patentIDs, paperIDs)VALUES(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getId());
            ptmt.setString(2, x.getPatentIDs());
            ptmt.setString(3,x.getPaperIDs());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加用户购买科技资源失败 用户购买科技资源表:[id:{}]", x.getId());
            return false;
        }
        logger.info("添加用户购买科技资源成功 用户购买科技资源表:[id:{}]", x.getId());
        return true;
    }
    //删除用户购买科技资源表
    public Boolean delBoughtScientificRes(int id) {
        logger.info("删除用户购买科技资源[id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM boughtScientificRes WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除用户购买科技资源失败 用户购买科技资源表:[id:{}]", id);
            return false;
        }
        logger.info("删除用户购买科技资源 用户购买科技资源表:[id:{}]", id);
        return true;
    }
//修改用户购买科技资源表

    public Boolean updateBoughtScientificRes(boughtScientificRes x) {
        logger.info("修改用户购买科技资源申请 [id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE boughtScientificRes " +
                "SET patentIDs=?,paperIDs=?" +
                "WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getId());
            ptmt.setString(2,x.getPatentIDs());
            ptmt.setString(3, x.getPaperIDs());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改用户购买科技资源失败 用户购买科技资源表:[id:{}]", x.getId());
            return false;
        }
        logger.info("修改用户购买科技资源成功 用户购买科技资源表:[id:{}]", x.getId());
        return true;
    }
    //查询用户购买科技资源表


    public boughtScientificRes queryBoughtScientificRes(int id) {
        logger.info("查询用户购买科技资源 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM boughtScientificRes WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在用户
                throw new SQLException();
            }
            boughtScientificRes ret = new boughtScientificRes();
            ret.setId(res.getInt("id"));
            ret.setPaperIDs(res.getString("patentIDs"));
            ret.setPaperIDs(res.getString("paperIDs"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的用户购买科技资源 [id:{}]", id);
            return null;
        }
    }

    //交易信息
    //添加交易信息
    public Boolean addTransaction(transaction x) {
        logger.info("添加交易信息[id:{}]", x.getTradeID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO transaction(tradeID,type, buyerID, itemID, tradeTime)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getTradeID());
            ptmt.setInt(2, x.getType());
            ptmt.setInt(3, x.getBuyerID());
            ptmt.setInt(4, x.getItemID());
            ptmt.setTime(5, x.getTradeTime());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加交易信息失败 交易信息:[tradeID:{}]", x.getTradeID());
            return false;
        }
        logger.info("添加交易信息成功 交易信息:[tradeID:{}]", x.getTradeID());
        return true;
    }

    //删除交易信息
    public Boolean delTransaction(int id) {
        logger.info("删除交易信息 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM transaction WHERE tradeID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除交易信息失败 交易信息:[id:{}]", id);
            return false;
        }
        logger.info("删除交易信息成功 交易信息:[id:{}]", id);
        return true;
    }

    //修改交易信息
    public Boolean updateTransaction(transaction x) {
        logger.info("修改交易信息 [id:{}]", x.getTradeID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE transaction " +
                "SET type=?,buyerID=?,itemID=?,tradeTime=? " +
                "WHERE tradeID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getType());
            ptmt.setInt(2, x.getBuyerID());
            ptmt.setInt(3, x.getItemID());
            ptmt.setTime(4, x.getTradeTime());
            ptmt.setInt(5, x.getTradeID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改交易信息失败 交易信息:[id:{}]", x.getTradeID());
            return false;
        }
        logger.info("修改交易信息成功 交易信息:[id:{}]", x.getTradeID());
        return true;
    }

    //查找交易信息
    public transaction queryTransaction(int id) {
        logger.info("查询交易信息 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM transaction WHERE tradeID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在交易信息
                throw new SQLException();
            }
            transaction ret = new transaction();
            ret.setTradeID(res.getInt("tradeID"));
            ret.setType(res.getInt ("type"));
            ret.setBuyerID(res.getInt("buyerID"));
            ret.setItemID(res.getInt("itemID"));
            ret.setTradeTime(res.getTime("tradeTime"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的交易信息 [id:{}]", id);
            return null;
        }
    }

    //日志
    //添加日志
    public Boolean addLogs(Logs x) {
        logger.info("添加日志[time:{}]", x.getTime());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO Logs(time, info)VALUES(?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setTimestamp(1, x.getTime());
            ptmt.setString(2, x.getInfo());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加日志失败 日志:[time:{}]", x.getTime());
            return false;
        }
        logger.info("添加日志成功 日志:[time:{}]", x.getTime());
        return true;
    }

    //删除日志
    public Boolean delLogs(Timestamp time) {
        logger.info("删除日志 [time:{}]", time);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM Logs WHERE time=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setTimestamp(1, time);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除日志失败 日志:[time:{}]", time);
            return false;
        }
        logger.info("删除日志成功 日志:[time:{}]", time);
        return true;
    }

    //修改日志
    public Boolean updateLogs(Logs x) {
        logger.info("修改日志 [time:{}]", x.getTime());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE Logs " +
                "SET info=? " +
                "WHERE time=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getInfo());
            ptmt.setTimestamp(2, x.getTime());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改日志失败 日志:[time:{}]", x.getTime());
            return false;
        }
        logger.info("修改日志成功 日志:[time:{}]", x.getTime());
        return true;
    }

    //查找日志
    public Logs queryLogs(Timestamp time) {
        logger.info("查询日志 [time:{}]", time);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM Logs WHERE time=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setTimestamp(1, time);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在日志
                throw new SQLException();
            }
            Logs ret = new Logs();
            ret.setTime(res.getTimestamp("time"));
            ret.setInfo(res.getString ("info"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该time的日志 [time:{}]", time);
            return null;
        }
    }

    //私信
//添加私信
    public Boolean addMessage(message x) {
        logger.info("添加私信[id:{}]", x.getMessageID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO message(messageID,senderID, recipientID, message, sendTime)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getMessageID());
            ptmt.setInt(2, x.getSenderID());
            ptmt.setInt(3, x.getRecipientID());
            ptmt.setString(4, x.getMessage());
            ptmt.setTime(5, x.getSendTime());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加私信失败 私信:[tradeID:{}]", x.getMessageID());
            return false;
        }
        logger.info("添加私信成功 私信:[tradeID:{}]", x.getMessageID());
        return true;
    }

    //删除私信
    public Boolean delMessage(int id) {
        logger.info("删除私信 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM message WHERE messageID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除私信失败 私信:[id:{}]", id);
            return false;
        }
        logger.info("删除私信成功 私信:[id:{}]", id);
        return true;
    }

    //修改私信
    public Boolean updateMessage(message x) {
        logger.info("修改私信 [id:{}]", x.getMessageID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE message " +
                "SET senderID=?,recipientID=?,message=?,sendTime=? " +
                "WHERE messageID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, x.getSenderID());
            ptmt.setInt(2, x.getRecipientID());
            ptmt.setString(3, x.getMessage());
            ptmt.setTime(4, x.getSendTime());
            ptmt.setInt(5, x.getMessageID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改私信失败 私信:[id:{}]", x.getMessageID());
            return false;
        }
        logger.info("修改私信成功 私信:[id:{}]", x.getMessageID());
        return true;
    }

    //查找私信
    public message queryMessage(int id) {
        logger.info("查询私信 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM message WHERE messageID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在私信
                throw new SQLException();
            }
            message ret = new message();
            ret.setMessageID(res.getInt("messageID"));
            ret.setSenderID(res.getInt ("senderID"));
            ret.setRecipientID(res.getInt("recipientID"));
            ret.setMessage(res.getString("message"));
            ret.setSendTime(res.getTime("sendTime"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的私信 [id:{}]", id);
            return null;
        }
    }

    //标签
//添加标签
    public Boolean addTags(tags x) {
        logger.info("添加标签[tag:{}]", x.getTag());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO tags(tag)VALUES(?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setString(1, x.getTag());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加标签失败 标签:[tradeID:{}]", x.getTag());
            return false;
        }
        logger.info("添加标签成功 标签:[tradeID:{}]", x.getTag());
        return true;
    }

    //删除标签
    public Boolean delTags(String tag) {
        logger.info("删除标签 [tag:{}]", tag);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM tags WHERE tag=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, tag);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除标签失败 标签:[tag:{}]", tag);
            return false;
        }
        logger.info("删除标签成功 标签:[tag:{}]", tag);
        return true;
    }

    //专家标签
//添加专家标签
    public Boolean addExpertTag(expertTag x) {
        logger.info("添加专家标签[id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO expertTag(id, tag)VALUES(?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getId());
            ptmt.setString(2, x.getTag());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加专家标签失败 专家标签:[tradeID:{}]", x.getId());
            return false;
        }
        logger.info("添加专家标签成功 专家标签:[tradeID:{}]", x.getId());
        return true;
    }

    //删除专家标签
    public Boolean delExpertTag(int id) {
        logger.info("删除专家标签 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM expertTag WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除专家标签失败 专家标签:[id:{}]", id);
            return false;
        }
        logger.info("删除专家标签成功 专家标签:[id:{}]", id);
        return true;
    }

    //修改专家标签
    public Boolean updateExpertTag(expertTag x) {
        logger.info("修改专家标签 [id:{}]", x.getId());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE expertTag " +
                "SET tag=? " +
                "WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getTag());
            ptmt.setInt(2, x.getId());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改专家标签失败 专家标签:[id:{}]", x.getId());
            return false;
        }
        logger.info("修改专家标签成功 专家标签:[id:{}]", x.getId());
        return true;
    }

    //查找专家标签
    public expertTag queryExpertTag(int id) {
        logger.info("查询专家标签 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM expertTag WHERE id=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在专家标签
                throw new SQLException();
            }
            expertTag ret = new expertTag();
            ret.setId(res.getInt("id"));
            ret.setTag(res.getString ("tag"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的专家标签 [id:{}]", id);
            return null;
        }
    }

    //科技成果标签
//添加科技成果标签
    public Boolean addScientificResTag(scientificResTag x) {
        logger.info("添加科技成果标签[id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO scientificResTag(resID, tag)VALUES(?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //设置参数
            ptmt.setInt(1, x.getResID());
            ptmt.setString(2, x.getTag());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("添加科技成果标签失败 科技成果标签:[tradeID:{}]", x.getResID());
            return false;
        }
        logger.info("添加科技成果标签成功 科技成果标签:[tradeID:{}]", x.getResID());
        return true;
    }

    //删除科技成果标签
    public Boolean delScientificResTag(int id) {
        logger.info("删除科技成果标签 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM scientificResTag WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("删除科技成果标签失败 科技成果标签:[id:{}]", id);
            return false;
        }
        logger.info("删除科技成果标签成功 科技成果标签:[id:{}]", id);
        return true;
    }

    //修改科技成果标签
    public Boolean updateScientificResTag(scientificResTag x) {
        logger.info("修改科技成果标签 [id:{}]", x.getResID());
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE scientificResTag " +
                "SET tag=? " +
                "WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, x.getTag());
            ptmt.setInt(2, x.getResID());
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("修改科技成果标签失败 科技成果标签:[id:{}]", x.getResID());
            return false;
        }
        logger.info("修改科技成果标签成功 科技成果标签:[id:{}]", x.getResID());
        return true;
    }

    //查找科技成果标签
    public scientificResTag queryScientificResTag(int id) {
        logger.info("查询科技成果标签 [id:{}]", id);
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM scientificResTag WHERE resID=?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (!res.next()) {//不存在科技成果标签
                throw new SQLException();
            }
            scientificResTag ret = new scientificResTag();
            ret.setResID(res.getInt("resID"));
            ret.setTag(res.getString ("tag"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("不存在该id的科技成果标签 [id:{}]", id);
            return null;
        }
    }



}