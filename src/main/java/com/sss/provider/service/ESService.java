package com.sss.provider.service;

import com.sss.interfaces.IESService;
import com.sss.provider.dao.ESdao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("ESService")
@Transactional
public class ESService implements IESService {
    public List<Map<String, Object>> FuzzyQueryAbstract(String info) {
        try {
            return ESdao.search("query", "abstract", info, 0, 100);
        } catch (Exception e) {
            System.out.println("IO Exception happens" + e.getMessage());
            return null;
        }
    }

    // TODO: TEST-OK ; no use
    public static void main(String []args) {
        // TODO: 修改查询代码, 现在的查询可能不对
        ESService testService = new ESService();
        List<Map<String, Object>> res = testService.FuzzyQueryAbstract("test");
        if (res==null) {
            System.out.println("数据获取失败");
            return;
        }
        for (Map<String, Object> now:res) {
            System.out.println("found");
            System.out.println(now.toString());
        }
        System.out.println("end");
    }
}
