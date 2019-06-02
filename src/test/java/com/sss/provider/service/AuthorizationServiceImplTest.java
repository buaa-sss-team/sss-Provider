package com.sss.provider.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AuthorizationServiceImplTest {
    @Autowired
    AuthorizationServiceImpl authorization;

    @Test
    public void userLogin() {
        authorization = new AuthorizationServiceImpl();
        assertFalse(authorization.userLogin("123" , "344") == 0);
        assertTrue(authorization.userLogin("test1" , "test1") == 0);
    }

    @Test
    public void userSignIn() {
    }
}