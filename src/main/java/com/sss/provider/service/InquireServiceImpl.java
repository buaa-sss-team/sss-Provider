package com.sss.provider.service;

import com.sss.interfaces.service.InquireService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sss.interfaces.hmodel.*;
@Service("InquireService")
@Transactional
public class InquireServiceImpl implements InquireService {
    @Autowired
    private HDBdao hdBdao;
    public User getUserInfo(int id) {

        return (User) hdBdao.getByID(User.class,id);
    }

    public User getUserInfo(String name) {

        return hdBdao.getUserByName(name);
    }

    public Expert getExperetInfo(int id) {

        return(Expert) hdBdao.getByID(Expert.class, id );
    }
}
