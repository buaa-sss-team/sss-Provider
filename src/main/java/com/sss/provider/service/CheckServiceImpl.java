package com.sss.provider.service;

import com.sss.interfaces.hmodel.*;
import com.sss.interfaces.service.CheckService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CheckService")
@Transactional
public class CheckServiceImpl implements CheckService {
    @Autowired
    private HDBdao hdBdao;
    public List<SettleIn> checkSettleIn() {

        return null;
    }

    public List<Payment> checkPayment() {

        return null;
    }

    public List<BuyRes> checkBuyRes() {

        return null;
    }

    public List<ModifySciRes> checkModifyRes() {

        return null;
    }

    public List<Auditapplication> checkAudApp(int status){
        return hdBdao.getAuditByStatus(status);
    }
}
