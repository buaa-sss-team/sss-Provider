package com.sss.provider.service;

import com.sss.interfaces.hmodel.*;
import com.sss.interfaces.service.CommonService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service("CommonService")
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    private HDBdao hdBdao;

    public int userLogin(String name, String pwd) {
        User user = hdBdao.getUserByName(name);
        if (user == null)
            return 1;
        if (!user.getPassword().equals(pwd))
            return 1;
        return 0;
    }

    public int userSignIn(User user) {
        if (user == null)
            return 1;
        else return hdBdao.insert(user);
    }

    public List<Tobeexpert> checkTobeexpert(int status) {
        return hdBdao.getTobeexpertBystatus(status);
    }

    public List<Buyres> checkBuyRes(int status) {
        return hdBdao.getBuyresBystatus(status);
    }

    public User getUserInfo(int id) {

        return (User) hdBdao.getByID(User.class,id);
    }

    public User getUserInfo(String name) {

        return hdBdao.getUserByName(name);
    }

    public Expert getExpertInfo(int id) {

        return(Expert) hdBdao.getByID(Expert.class, id );
    }

    public Paper getPaperInfo(int id) {
        return (Paper) hdBdao.getByID(Paper.class,id);
    }

    public Patent getPatentInfo(int id) {

        return (Patent) hdBdao.getByID(Patent.class,id);
    }

    public Action getActionInfo(int id) {
        return (Action) hdBdao.getByID(Action.class,id);
    }

    public List<Tradeinfo> getTradeInfo(int id) {

        return hdBdao.getTradeInfoByUserId(id);
    }

    public int reqForExpert(Tobeexpert tobeexpert) {
        if (tobeexpert == null)
            return 1;
        else return hdBdao.insert(tobeexpert);
    }

    public int reqForWithdraw(Payment payment) {
        if (payment==null)
            return 1;
        else return hdBdao.insert(payment);
    }

    public int buyResource(Buyres buyRes) {
        if (buyRes == null)
            return 1;
        else return hdBdao.insert(buyRes);
    }

    public int addExpert(Expert expert) {
        if (expert == null || expert.getId() == null)
            return 1;
        if (hdBdao.getByID(User.class,expert.getId()) == null)
            return 1;
        return hdBdao.insert(expert);
    }

    public int delete(Object object) {
        return hdBdao.delete(object);
    }

    public int update(Object object) {
        return hdBdao.update(object);
    }

    public int insert(Object object) {
        return hdBdao.insert(object);
    }

    public int updateExpertInfo(Expert expert){
        if (expert == null)
            return 1;
        else return hdBdao.update(expert);
    }

    public int updateUserInfo(User user) {
        if (user == null)
            return 1;
        else return hdBdao.update(user);
    }

    public int addTradeInfo(Tradeinfo tradeinfo) {
        if (tradeinfo == null)
            return 1;
        else return hdBdao.update(tradeinfo);
    }

    public List<Action> getActionByUserId(int id) {
        return hdBdao.getActionByUserID(id);
    }

    public int addAction(Action action) {
        if (action == null)
            return 1;
        else return hdBdao.insert(action);

    }


}
