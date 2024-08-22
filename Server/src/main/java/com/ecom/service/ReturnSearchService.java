package com.ecom.service;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.pojo.entity.ReturnSimple;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReturnSearchService {


    List<Return> getByRDO(SearchByRDODTO searchByRDODTO);

    List<Return> getByPO(SearchByRDODTO searchByRDODTO);

    void upload(MultipartFile file);

    List<ReturnSimple> getByRDOSimple(SearchByRDODTO searchByRDODTO);

    void uploadSimple(MultipartFile file);
}
