package com.ecom.service.impl;

import com.ecom.mapper.ReturnMapper;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.service.ReturnSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnSearchServiceImpl implements ReturnSearchService {

    @Autowired
    ReturnMapper returnMapper;

    @Override
    public List<Return> getByRDO(SearchByRDODTO searchByRDODTO) {

        returnMapper.createTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<Return> returnResult = returnMapper.searchByRDO(searchByRDODTO);

        return returnResult;
    }
}
