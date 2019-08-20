package com.ck.orangeblogcommon.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ck
 * @date 2019/5/21 10:30
 * Description  : es工具类
 */
@Component
public class EsUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(EsUtil.class);

    @Resource(name = "rhlClient")
    private RestHighLevelClient rhlClient ;

    @Resource(name = "restClient")
    private RestClient restClient ;

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    public boolean createIndex(String index) {
        //index名必须全小写，否则报错
        CreateIndexRequest request = new CreateIndexRequest(index);
        try {
            CreateIndexResponse indexResponse = rhlClient.indices().create(request);
            if (indexResponse.isAcknowledged()) {
                LOGGER.info("创建索引成功");
            } else {
                LOGGER.info("创建索引失败");
            }
            return indexResponse.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 插入数据
     * @param index
     * @param type
     * @param object
     * @return String
     */
    public String addData(String index,String type,String id,JSONObject object) {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        try {
            indexRequest.source(object.toJSONString(), XContentType.JSON);
            IndexResponse indexResponse = rhlClient.index(indexRequest);
            return indexResponse.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量添加数据
     * @param index
     * @param type
     * @param jsonArray
     * @return boolean
     */
    public boolean bulkAddData(String index, String type, JSONArray jsonArray, String id){
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            IndexRequest indexRequest = new IndexRequest(index, type, jsonObject.getString(id));
            indexRequest.source(jsonObject.toJSONString(), XContentType.JSON);
            request.add(indexRequest);
        }
        try {
            BulkResponse bulkResponse = rhlClient.bulk(request);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除数据
     * @param index
     * @param type
     * @param id
     */
    public boolean deleteData(String index,String type,String id){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index(index);
        deleteRequest.type(type);
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = rhlClient.delete(deleteRequest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除整个index
     * @param index
     * @return boolean
     */
    public boolean deleteIndex(String index){
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(index);
        try {
            rhlClient.indices().delete(deleteIndexRequest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 取得数据
     * @param index
     * @param type
     * @param id
     * @return String
     */
    public String getData(String index, String type, String id){
        return null;
    }

    /**
     * 查询数据
     * @param index
     * @param type
     * @param fields
     * @param value
     * @return JSONArray
     */
    public JSONArray searchData(String index, String type, String[] fields, String value){
        JSONArray jsonArray = new JSONArray();
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(value, fields);
        multiMatchQueryBuilder.fuzziness(Fuzziness.AUTO);
        multiMatchQueryBuilder.prefixLength(3);
        multiMatchQueryBuilder.maxExpansions(10);
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = rhlClient.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                String sourceAsString = hit.getSourceAsString();
                jsonArray.add(JSONObject.parseObject(sourceAsString));
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("查询ES数据出错");
        }
        return jsonArray;
    }

    /**
     * 检查索引
     * @param index
     * @return
     * @throws IOException
     */
    public boolean checkIndexExist(String index) {
        try {
            Response response = rhlClient.getLowLevelClient().performRequest("HEAD", index);
            boolean exist = response.getStatusLine().getReasonPhrase().equals("OK");
            return exist;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取低水平客户端
     * @return
     */
    public RestClient getLowLevelClient() {
        return rhlClient.getLowLevelClient();
    }

}
