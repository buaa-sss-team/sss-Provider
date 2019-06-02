package com.sss.provider.service;

import com.sss.interfaces.hmodel.User;
import com.sss.interfaces.service.AuthorizationService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Authorization")
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
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
        else
            hdBdao.insert(user);
        return 0;
    }
}
