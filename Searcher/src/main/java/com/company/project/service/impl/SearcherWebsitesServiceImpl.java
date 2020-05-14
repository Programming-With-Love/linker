package com.company.project.service.impl;

import com.company.project.dao.SearcherWebsitesMapper;
import com.company.project.model.SearcherWebsites;
import com.company.project.service.SearcherWebsitesService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/05/14.
 */
@Service
@Transactional
public class SearcherWebsitesServiceImpl extends AbstractService<SearcherWebsites> implements SearcherWebsitesService {
    @Resource
    private SearcherWebsitesMapper searcherWebsitesMapper;

    @Override
    public SearcherWebsites findInfoById(String id) {

        return searcherWebsitesMapper.selectInfoById(id);
    }

    @Override
    public void deleteInfoById(String id) {
        searcherWebsitesMapper.deleteInfoById(id);
    }
}
