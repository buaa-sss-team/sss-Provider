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
    public List<Map<String, Object>> FuzzyQueryAbstract(String name, String index,String info,int count) {
        if (count < 100) count = 100;
        try {
            name = name.toLowerCase()+"res";
            ESdao.setIndexName(name);
            return ESdao.search("query", index, info, 0, count);
        } catch (Exception e) {
            System.out.println("IO Exception happens\n  " + e.getMessage());
            return null;
        }
    }

    // TODO: TEST-OK ; no use
    // 在这里删索引啥的
    public static void main(String []args) {
        // TODO: 修改查询代码, 现在的查询可能不对
        ESService testService = new ESService();
//        try{
//            ESdao.deleteIndex("indexres");
//        }catch (Exception e){
//            System.out.println(e);
//        }
        List<Map<String, Object>> res = testService.FuzzyQueryAbstract("expert", "position", "posion", 0);
        if (res == null) {
            System.out.println("数据获取失败");
            return;
        }
        for (Map<String, Object> now : res) {
            System.out.println("found");
            System.out.println(now.toString());
        }
        System.out.println("end");
    }
}
