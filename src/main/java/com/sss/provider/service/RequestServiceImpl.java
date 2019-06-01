package com.sss.provider.service;

import com.sss.interfaces.hmodel.BuyRes;
import com.sss.interfaces.hmodel.ModifySciRes;
import com.sss.interfaces.hmodel.Payment;
import com.sss.interfaces.hmodel.SettleIn;
import com.sss.interfaces.service.RequestService;
import com.sss.provider.dao.HDBdao;

public class RequestServiceImpl implements RequestService {
    private HDBdao hdBdao;
    public void reqForExpert(SettleIn settleIn) {
        hdBdao.insert(settleIn);
        return ;
    }

    public void reqForWithdraw(Payment payment) {
        hdBdao.insert(payment);
    }

    public void buyResource(BuyRes buyRes) {
        hdBdao.insert(buyRes);
    }

    public void modifyResource(ModifySciRes modifySciRes) {

    }
}
