package com.company.project.service.impl;


import com.company.project.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author aturx
 * @version 0.1
 * @date 2020/06/04 10:06
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private SolrClient client;
    /**
     * 高亮查询
     */
    @Override
    public SolrDocumentList querySolr(String collection, String q, String field, String pageNumber) throws IOException, SolrServerException {
        SolrQuery params = new SolrQuery();
        //查询条件, 这里的 q 对应 下面图片标红的地方
        params.set("q", q);
        //过滤条件
         params.set("fq", "cent:[1 TO 100]");
        //排序
         params.addSort("cent", SolrQuery.ORDER.asc);
        //分页
        params.setStart((Integer.parseInt(pageNumber) - 1) * 20);
        params.setRows(20);
        //默认域
        params.set("df", field);
        //只查询指定域
        // params.set("fl", content + ",content,id,title,author");
        //高亮
        //打开开关
        params.setHighlight(true);
        //指定高亮域
        params.addHighlightField("id");
        params.addHighlightField(field);
        params.addHighlightField("webName");
        params.addHighlightField("webUrl");
        params.addHighlightField("webDoc");
        params.addHighlightField("cent");


        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");

        QueryResponse queryResponse = client.query(collection,params);

        SolrDocumentList results = queryResponse.getResults();

        long numFound = results.getNumFound(); // 查询到的结果

        //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

        for(SolrDocument result : results){ // 将高亮结果合并到查询结果中
//            result.remove("keyword");
            highlight.forEach((k,v) ->{
                if(result.get("id").equals(k)){
                    v.forEach((k1,v1) -> {
                        if(!k1.equals("keyword")) result.setField(k1,v1); // 高亮列合并如结果
                    });
                }
            });
        }
        return results;
    }
    /**
     * 根据ID查询
     */
    @Override
    public SolrDocument getById(String collection, String id) throws IOException, SolrServerException {
        return client.getById(id);
    }

    /**
     * 删除全部索引
     * @param collection
     */
    @Override
    public void deleteAll(String collection) throws IOException, SolrServerException {
        client.deleteByQuery(collection,"*:*");
        client.commit(collection);
    }
}
