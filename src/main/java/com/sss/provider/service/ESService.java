package com.sss.provider.service;

import com.sss.interfaces.IESService;
import com.sss.provider.dao.ESdao;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("ESService")
@Transactional
public class ESService implements IESService {
    private static Set<String> indexNames = new TreeSet<String>() {
        {
            add("expert");
            add("paper");
            add("patent");
        }
    };

    public List<Map<String, Object>> FuzzyQuery(String name, String index, String info, int count) {
        if (count < 100) count = 100;
        try {
            assert (indexNames.contains(name));
            name = name.toLowerCase() + "res";
            ESdao.setIndexName(name);
            return ESdao.search("query", index, info, 0, count);
        } catch (Exception e) {
            System.out.println("IO Exception happens\n  " + e.getMessage());
            return null;
        }
    }

    public List<Map<String, Object>> Query(String name, List<Pair<String, List<Pair<String, Pair<String, String>>>>> limits, int count) {
        if (count < 100) count = 100;
        try {
            assert (indexNames.contains(name));
            name = name.toLowerCase() + "res";
            ESdao.setIndexName(name);
            return ESdao.searchMultiple("query", limits, 0, count);
        } catch (Exception e) {
            System.out.println("IO Exception happens\n  " + e.getMessage());
            return null;
        }
    }

    // TODO: TEST-OK ; no use
    // 在这里删索引啥的
    public static void main(String[] args) {
        // TODO: 修改查询代码, 现在的查询可能不对
        ESService testService = new ESService();
        /*
        // 更改数据库
        try{
            ESdao.deleteIndex("indexres");
        }catch (Exception e){
            System.out.println(e);
        }
        */
        /*
        // testFuzzyQuery
        List<Map<String, Object>> res = testService.FuzzyQuery("expert", "position", "posion", 0);
        if (res == null) {
            System.out.println("数据获取失败");
            return;
        }
        for (Map<String, Object> now : res) {
            System.out.println("found");
            System.out.println(now.toString());
        }
        System.out.println("end");
        */
        List<Pair<String, List<Pair<String, Pair<String, String>>>>> limits = new LinkedList<Pair<String, List<Pair<String, Pair<String, String>>>>>();
        LinkedList<Pair<String, Pair<String, String>>> fir=new LinkedList<Pair<String, Pair<String, String>>>();
        fir.add(new Pair("instruction",new Pair("matchQuery","sss")));
        limits.add(new Pair("abstract",fir));
        List<Map<String, Object>> res = testService.Query("paper",limits, 100);
        if (res == null) {
            System.out.println("数据获取失败");
            return;
        }
        for (Map<String, Object> now : res) {
            System.out.println(now.toString());
        }
    }
}
