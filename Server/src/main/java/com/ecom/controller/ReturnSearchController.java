package com.ecom.controller;


import com.ecom.common.exception.ExtensionNotCorrectException;
import com.ecom.common.result.Result;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.pojo.entity.ReturnSimple;
import com.ecom.service.ReturnSearchService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/ageReturn")
@Tag(name = "ReturnSearch")
@Slf4j
public class ReturnSearchController {

    @Autowired
    private ReturnSearchService returnSearchService;




    @PostMapping("/byRDO")
    public Result<List<Return>> searchByRDO(@RequestBody SearchByRDODTO searchByRDODTO){

        log.info("search by RDO starts");

        List<Return> returnResult = returnSearchService.getByRDO(searchByRDODTO);

        return Result.success(returnResult);
    }

    @PostMapping("/byPO")
    public Result<List<Return>> searchByPO(@RequestBody SearchByRDODTO searchByRDODTO){

        log.info("search by RDO starts");

        List<Return> returnResult = returnSearchService.getByPO(searchByRDODTO);

        return Result.success(returnResult);
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file){

        returnSearchService.upload(file);

        return Result.success();
    }

    @PostMapping("/byRDOSimple")
    public Result<List<ReturnSimple>> searchByRDOSimple(@RequestBody SearchByRDODTO searchByRDODTO){

        List<ReturnSimple> returnResult = returnSearchService.getByRDOSimple(searchByRDODTO);

        return Result.success(returnResult);
    }

    @PostMapping("/uploadSimple")
    public Result uploadSimple(MultipartFile file){

        returnSearchService.uploadSimple(file);

        return Result.success();
    }



}
