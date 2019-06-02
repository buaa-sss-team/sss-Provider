package com.sss.provider.service;

import com.sss.interfaces.hmodel.*;
import com.sss.interfaces.service.CheckService;
import com.sss.provider.dao.HDBdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.HashDocAttributeSet;
import java.util.List;

@Service("CheckService")
@Transactional
public class CheckServiceImpl implements CheckService {
    @Autowired
    private HDBdao hdBdao;
    public List<Tobeexpert> checkTobeexpert(int status) {
        return hdBdao.getTobeexpertBystatus(status);
    }

    public List<Buyres> checkBuyRes(int status) {
        return hdBdao.getBuyresBystatus(status);
    }

}
