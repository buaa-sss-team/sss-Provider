package com.sss.provider.service;

import com.sss.provider.util.impl.DBService;
import com.sss.interfaces.model.user;
import com.sss.interfaces.service.IAuthorization;

public class IAuthorizationImpl implements IAuthorization {

    private DBService dbService;

    public int userLogin(user user) {
        return 0;
    }

    public int userSignIn(String acc, String pwd) {
        return 0;
    }
}
