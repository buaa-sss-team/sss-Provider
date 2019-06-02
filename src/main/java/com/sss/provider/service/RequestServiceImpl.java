package com.sss.provider.service;

import com.sss.interfaces.hmodel.BuyRes;
import com.sss.interfaces.hmodel.ModifySciRes;
import com.sss.interfaces.hmodel.Payment;
import com.sss.interfaces.hmodel.SettleIn;
import com.sss.interfaces.service.RequestService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("RequestService")
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    private HDBdao hdBdao;

    public void reqForExpert(SettleIn settleIn) {
        hdBdao.insert(settleIn);
    }

    public void reqForWithdraw(Payment payment) {
        hdBdao.insert(payment);
    }

    public void buyResource(BuyRes buyRes) {
        hdBdao.insert(buyRes);
    }

    public void modifyResource(ModifySciRes modifySciRes) {
        hdBdao.update(modifySciRes);
    }
}
