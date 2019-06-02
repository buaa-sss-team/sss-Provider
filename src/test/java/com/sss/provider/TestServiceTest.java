package com.sss.provider;

import com.sss.interfaces.service.IAuthorization;
import com.sss.provider.service.IAuthorizationImpl;
import com.sss.provider.util.impl.TestService;
import com.sss.provider.service.AuthorizationImpl;
import com.sss.interfaces.ITestService;
import com.sss.interfaces.MyReturnClass;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO 在这里编写并完善你自己实现的类的单元测试

/***
 * 这是对TestService的单元测试
 * 当然这是一句废话
 * 你也可以不写
 * 你的单元测试必须要在不依赖本项目其他模块的前提下运行
 * 否则出了错也有可能是别人的锅
 * 换句话说不能使用dubbo，直接new一个你的对象来用
 * 如果有数据库访问需求的话也直接根据你的测试条件伪造返回值
 * 总之就是给你的代码编写一套评测系统
 * 必须保证正确的代码怎么跑都是正确的（不论外界条件），错误的代码只要有一点错就会马上过不了
 */
public class TestServiceTest {
    private ITestService testService;
    private IAuthorization iAuthorization;
    private AuthorizationImpl authorization;
    private Logger logger= LoggerFactory.getLogger(TestServiceTest.class);

    @Before
    public void init(){
        testService=new TestService();
        iAuthorization=new IAuthorizationImpl();
        authorization = new AuthorizationImpl();
    }

    /***
     * 尽量覆盖所有代码和条件分支
     */
    @Test
    public void testSignin(){
    }

    @Test
    public void testLogin(){
        logger.info(authorization.userLogin("springPig", "mima")+"");
        logger.info(authorization.userLogin("springPig", "xixixixi")+"");
    }

    /***
     * 这个是凑数瞎写的不要学我
     */


}
