package com.ecom.service.impl;


import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.common.utils.exportToCSVUtil;
import com.ecom.mapper.mysql.CStockMapper;
import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.entity.MinCStock;
import com.ecom.pojo.entity.MinCStockSummary;
import com.ecom.pojo.vo.MinCStockSummaryVO;
import com.ecom.pojo.vo.MinCStockVO;
import com.ecom.service.CStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ecom.common.utils.ConvertSKU;

@Service
public class CStockServiceImpl implements CStockService {


    @Autowired
    private CStockMapper cStockMapper;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Override
    public List<MinCStockVO> getMinQtyCStock() {

        List<MinCStock> minCStocks = cStockMapper.getMinQty();
        List<MinCStockVO> result = new ArrayList<>();
        for(MinCStock m: minCStocks){
            MinCStockVO mcs = new MinCStockVO(m.getItem(),m.getMinQty().trim(), ConvertSKU.convert(m.getItem()));
            result.add(mcs);
        }

        return result;
    }

    @Override
    public List<MinCStockSummaryVO> getMinQtyCStockSummary() {
        List<MinCStock> minCStocks = cStockMapper.getMinQty();
        List<MinCStockVO> result = new ArrayList<>();
        for(MinCStock m: minCStocks){
            MinCStockVO mcs = new MinCStockVO(m.getItem(),m.getMinQty().trim(), ConvertSKU.convert(m.getItem()));
            result.add(mcs);
        }
        List<MinCStockSummaryVO> resultSummary = result.stream().collect(Collectors.groupingBy(MinCStockVO::getCategory,
                                                                                                Collectors.summingInt(x -> Integer.parseInt(x.getMinQty()) ))).
                                                                                                entrySet().stream().map(e -> new MinCStockSummaryVO(e.getKey(),e.getValue())).
                                                                                                collect(Collectors.toList());

        return resultSummary;
    }

    @Override
    public void uploadPUMI(List<PUMIDTO> pumidtos) {

    }

    public void prepareData(){
        cStockMapper.truncateTable("stg_location");
        cStockMapper.truncateTable("location");
        cStockMapper.truncateTable("stg_location_nerp");
        cStockMapper.truncateTable("location_nerp");
        // load into stg table
        String filePath = localFolderUtil.getUpdateTime("locationInventory.txt").get("filepath");
        cStockMapper.updateStgTable(filePath,0,"stg_location");
        filePath = localFolderUtil.getUpdateTime("locationNERP.txt").get("filepath");
        cStockMapper.updateStgTable(filePath,3,"stg_location_nerp");
        //load into table
        cStockMapper.insertNERP();
        cStockMapper.insertSynapse();

    }

}
