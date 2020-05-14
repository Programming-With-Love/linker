package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.SearcherWebsites;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;

public interface SearcherWebsitesMapper extends Mapper<SearcherWebsites> {

    @Select("select * from searcher_websites where id=#{id} ")
    SearcherWebsites selectInfoById(@Param("id") String id);

    @Delete("delete from searcher_websites where id=#{id} ")
    void deleteInfoById(@Param("id") String id);

}