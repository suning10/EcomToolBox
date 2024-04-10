package com.ecom.service;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;

import java.util.List;

public interface ReturnSearchService {


    List<Return> getByRDO(SearchByRDODTO searchByRDODTO);
}
