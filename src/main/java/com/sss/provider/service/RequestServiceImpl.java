package com.sss.provider.service;

import com.sss.interfaces.service.RequestService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sss.interfaces.hmodel.*;

@Service("RequestService")
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    private HDBdao hdBdao;

    public int reqForExpert(Tobeexpert tobeexpert) {
        if (hdBdao.insert(tobeexpert) == 1)
            return 1;
        else return 0;
    }

    public int reqForWithdraw(Payment payment) {
        if (hdBdao.insert(payment)==1)
            return 1;
        else return 0;
    }

    public int buyResource(Buyres buyRes) {
        if (hdBdao.insert(buyRes)==1)
            return 1;
        else return 0;
    }

}
