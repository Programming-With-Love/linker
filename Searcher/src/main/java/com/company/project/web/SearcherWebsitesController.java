package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SearcherWebsites;
import com.company.project.service.SearcherWebsitesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/05/14.
*/
@CrossOrigin
@RestController
@RequestMapping("/searcher/websites")
public class SearcherWebsitesController {
    @Resource
    private SearcherWebsitesService searcherWebsitesService;

    @PostMapping("/add")
    public Result add(SearcherWebsites searcherWebsites) {
        searcherWebsitesService.save(searcherWebsites);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        searcherWebsitesService.deleteInfoById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SearcherWebsites searcherWebsites) {
        searcherWebsitesService.update(searcherWebsites);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SearcherWebsites searcherWebsites = searcherWebsitesService.findInfoById(id);
        return ResultGenerator.genSuccessResult(searcherWebsites);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SearcherWebsites> list = searcherWebsitesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
