package com.ecom.controller;


import com.ecom.common.result.Result;
import com.ecom.pojo.dto.HybrisReturnDTO;
import com.ecom.service.ReturnService;
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
@RequestMapping("hybris-return")
@Tag(name = "ReturnSearch")
@Slf4j
public class ReturnHybrisController {

    @Autowired
    ReturnService returnService;

    @PostMapping("/find-by-po")
    public Result<List<HybrisReturnDTO>> getHybrisReturn(@RequestBody List<String> po_list){
        List<HybrisReturnDTO> res = returnService.getByPo(po_list);
        return Result.success(res);
    }

    @PostMapping("/upload")
    public Result uploadHybris(MultipartFile file){
        returnService.uploadstg(file);
        return Result.success();
    }
}
