package com.ecom.service;

import com.ecom.pojo.dto.HybrisReturnDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ReturnService {
    List<HybrisReturnDTO> getByPo(List<String> poList);


    void uploadstg(MultipartFile file);
}
