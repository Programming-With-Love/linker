package com.company.project.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * @author aturx
 * @version 0.1
 * @date 2020/06/04 10:06
 */
public interface SolrService {
    /**
     * solr 查询
     */
    SolrDocumentList querySolr(String collection, String q, String field, String pageNumber) throws IOException, SolrServerException;
    /**
     * 根据ID查询
     */
    SolrDocument getById(String collection, String id) throws IOException, SolrServerException;

    /**
     * 删除全部索引
     * @param collection
     */
    void deleteAll(String collection) throws IOException, SolrServerException;
}
