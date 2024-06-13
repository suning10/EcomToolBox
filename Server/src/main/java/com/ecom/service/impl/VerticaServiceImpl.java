package com.ecom.service.impl;

import com.ecom.mapper.VerticaMapper;
import com.ecom.service.VerticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerticaServiceImpl implements VerticaService {

    @Autowired
    private VerticaMapper verticaMapper;
    @Override
    public List<String> testQuery() {
        return verticaMapper.test();
    }
}
