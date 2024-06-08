package com.ecom.service;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReturnSearchService {


    List<Return> getByRDO(SearchByRDODTO searchByRDODTO);

    List<Return> getByPO(SearchByRDODTO searchByRDODTO);

    void upload(MultipartFile file);
}
