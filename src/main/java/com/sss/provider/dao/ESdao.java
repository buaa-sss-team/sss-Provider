package com.sss.provider.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.*;
import org.w3c.dom.ranges.Range;

import java.io.IOException;
import java.util.*;

public class ESdao {
    // 132.232.169.70:  服务器
    // 192.168.149.132: 虚拟机
    private final static String HOST = "132.232.169.70";
    private final static int PORT = 9200;//http请求的端口是9200，客户端是9300

    private static String indexName = "indexres";
    // 初始化api客户端
    private static RestHighLevelClient client =
            new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, PORT, "http")));

    // 关键字搜索 指定匹配类型
    public static List<Map<String, Object>> search(String type, String fieldName, String keyword,
                                                   int start, int count) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //关键字匹配对应字段
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, keyword);
        //模糊匹配
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        sourceBuilder.query(matchQueryBuilder);
        //确定要开始搜索的结果索引
        sourceBuilder.from(start);
        //返回的搜索匹配数
        sourceBuilder.size(count);
        searchRequest.source(sourceBuilder);
        searchRequest.types(type);
        //匹配度从高到低
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        List<Map<String, Object>> matchResult = new LinkedList<Map<String, Object>>();
        for (SearchHit hit : hits.getHits()) {
            matchResult.add(hit.getSourceAsMap());
        }
        return matchResult;
    }

    public static List<Map<String, Object>> searchMultiple(String type, List<Pair<String,List<Pair<String,Pair<String,String>>>>> limits,
                                                           int start, int count) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder andBoolQueryBuilder = new BoolQueryBuilder();
        for (Pair<String,List<Pair<String,Pair<String,String>>>> limit:limits){
            BoolQueryBuilder orBoolQueryBuilder = new BoolQueryBuilder();
            String name = limit.getKey();
            for (Pair<String,Pair<String,String>> orVal:limit.getValue()) {
                String value=orVal.getKey(),way=orVal.getValue().getKey(),fuzz=orVal.getValue().getValue();
                QueryBuilder QueryBuilder;
                if (way.equals("matchQuery")) {
                    QueryBuilder = new MatchQueryBuilder(name,value);
                } else if (way.equals("fuzzyQuery")) {
                    QueryBuilder = new FuzzyQueryBuilder(name,value);
                } else if (way.equals("rangeQuery")) {
                    if (way.equals("gt"))
                        QueryBuilder = new RangeQueryBuilder(name).gt(Integer.parseInt(fuzz));
                    else if (way.equals("lt"))
                        QueryBuilder = new RangeQueryBuilder(name).lt(Integer.parseInt(fuzz));
                    else if (way.equals("lte"))
                        QueryBuilder = new RangeQueryBuilder(name).lte(Integer.parseInt(fuzz));
                    else if (way.equals("gte"))
                        QueryBuilder = new RangeQueryBuilder(name).gte(Integer.parseInt(fuzz));
                    else {
                        System.out.println("no! Query rangeQuery 失败");
                        continue;
                    }
                } else if (way.equals("termQuery")) {
                    QueryBuilder=new TermQueryBuilder(name,value);
                } else if (way.equals("wildcardQuery")){
                    QueryBuilder=new WildcardQueryBuilder(name,value);
                } else {
                    System.out.println("no! Query way读取失败");
                    continue;
                }
                orBoolQueryBuilder.should(QueryBuilder);
            }
            andBoolQueryBuilder.must(orBoolQueryBuilder);
        }
        sourceBuilder.query(andBoolQueryBuilder);
        //确定要开始搜索的结果索引
        sourceBuilder.from(start);
        //返回的搜索匹配数
        sourceBuilder.size(count);
        searchRequest.source(sourceBuilder);
        searchRequest.types(type);
        //匹配度从高到低
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        List<Map<String, Object>> matchResult = new LinkedList<Map<String, Object>>();
        for (SearchHit hit : hits.getHits()) {
            matchResult.add(hit.getSourceAsMap());
        }
        return matchResult;
    }


    // 获得指定type指定id的数据 json
    public static Map getDocument(String type, String id) throws IOException {
        // TODO Auto-generated method stub
        GetRequest request = new GetRequest(indexName, type, id);
        GetResponse response = client.get(request);
        if (!response.isExists()) {
            System.out.println("检查到服务器上 " + type + " id=" + id + "不存在");
            return null;
        } else {
            String source = response.getSourceAsString();
            System.out.print("获取到服务器上 " + type + " id=" + id + "内容是：");
            System.out.println(source);
            return response.getSourceAsMap();
        }
    }

    public static boolean checkExistIndex(String indexName) throws IOException {
        boolean result =true;
        try {
            OpenIndexRequest openIndexRequest = new OpenIndexRequest(indexName);
            client.indices().open(openIndexRequest).isAcknowledged();
        } catch (ElasticsearchStatusException ex) {
            String m = "Elasticsearch exception [type=index_not_found_exception, reason=no such index]";
            if (m.equals(ex.getMessage())) {
                result = false;
            }
        }
        if(result)
            System.out.println("索引:" +indexName + " 是存在的");
        else
            System.out.println("索引:" +indexName + " 不存在");
        return result;
    }

    public static void deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        client.indices().delete(request);
        System.out.println("删除了索引："+indexName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void createIndex(String indexName) throws IOException {
        // TODO Auto-generated method stub
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        client.indices().create(request);
        System.out.println("创建了索引："+indexName);
    }
    public static String getIndexName() {
        return indexName;
    }
    public static void setIndexName(String indexName) {
        ESdao.indexName = indexName;
    }
    public static RestHighLevelClient getClient() {
        return client;
    }
    public static void setClient(RestHighLevelClient client) {
        ESdao.client = client;
    }
}