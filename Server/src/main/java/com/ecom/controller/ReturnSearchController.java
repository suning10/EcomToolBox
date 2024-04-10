package com.ecom.controller;


import com.ecom.common.result.Result;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.service.ReturnSearchService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/return")
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

}
