package com.sss.provider.service;

import com.sss.interfaces.service.Authorization;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Authorization")
@Transactional
public class AuthorizationImpl implements Authorization {
    @Autowired
    private HDBdao hdBdao;

    public int userLogin(String name, String pwd) {
        List<User> list = hdBdao.getUserByName(name);
        if (list.isEmpty())
            return 1;
        User user = list.get(0);
        if (!user.getPassword().equals(pwd))
            return 1;
        return 0;
    }

    public int userSignIn(User user) {
        hdBdao.insert(user);
        return 0;
    }
}
