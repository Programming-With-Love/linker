package com.company.project.web;


import com.company.project.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;


/**
 * @author aturx
 * @version 0.1
 * @date 2020/06/04 10:06
 */
@CrossOrigin
@RestController
public class SolrQueryController {



    private final String db_core = "linker_core";
    private final String file_core = "file_core";
    private final String fileDataImport = file_core + "/dataimport?command=full-import&clean=false&commit=true&entity=file";

    @Value("${spring.data.solr.host}")
    private String solrHost;
    @Resource
    private SolrClient client;
    @Resource
    private SolrService solrService;



    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search")
    public SolrDocumentList search(@RequestParam String q, @RequestParam String pageNumber){
        SolrDocumentList results = null;
        try {
            results = solrService.querySolr(db_core,q,"webDoc", pageNumber);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return results;
    }


    /**
     * 新增/修改 索引
     * 当 id 存在的时候, 此方法是修改(当然, 我这里用的 uuid, 不会存在的), 如果 id 不存在, 则是新增
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestParam String key, @RequestParam String content) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField(key, content);
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */
            client.add(db_core, doc);
            //client.commit();
            client.commit(db_core);
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById(db_core,id);
            client.commit(db_core);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 删除所有的索引
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {
            solrService.deleteAll(db_core);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById/{id}")
    public String getById(@PathVariable String id) throws Exception {
        SolrDocument document = solrService.getById(db_core,id);
        System.out.println(document);
        return document.toString();
    }



    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("doc/search/{q}")
    public SolrDocumentList docSearch(@PathVariable String q){
        SolrDocumentList results = null;
        try {
            results = solrService.querySolr(file_core,q,"keyword","1");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return results;
    }


}
