package com.company.project.service;
import com.company.project.model.SearcherWebsites;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/05/14.
 */
public interface SearcherWebsitesService extends Service<SearcherWebsites> {

    SearcherWebsites findInfoById(String id);

    void deleteInfoById(String id);

}
