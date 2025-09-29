package com.ecom.service.impl;


import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.CStockMapper;
import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.dto.skuCategoryDTO;
import com.ecom.pojo.entity.MinCStock;
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
    public List<MinCStock> getMinQtyCStock() {

        List<MinCStock> minCStocks = cStockMapper.getMinQty();


        return minCStocks;
    }

    @Override
    public List<MinCStockSummaryVO> getMinQtyCStockSummary() {
        List<MinCStockSummaryVO> minCStocksSummary = cStockMapper.getMinQtySummary();
//        List<MinCStockSummaryVO> resultSummary = minCStocks.stream().collect(Collectors.groupingBy(MinCStock::getCategory,
//                                                                                                Collectors.summingInt(x -> Integer.parseInt(x.getMinQty()) ))).
//                                                                                                entrySet().stream().map(e -> new MinCStockSummaryVO(e.getKey(),e.getValue())).
//                                                                                                collect(Collectors.toList());

        return minCStocksSummary;
    }

    @Override
    public void uploadPUMI(List<PUMIDTO> pumidtos) {

    }

    @Override
    public void uploadSKUCategory(List<skuCategoryDTO> skuCategoryDTOS) {
        cStockMapper.insertSkuCategory(skuCategoryDTOS);
    }

    @Override
    public List<skuCategoryDTO> getSkuCateogy() {
        List<skuCategoryDTO> result = cStockMapper.getSkuCategory();
        return result;
    }

    @Override
    public void delSkuCateogy(String sku) {
        cStockMapper.delSkuCategory(sku);
    }

    @Override
    public void updateSkuCateogy(skuCategoryDTO skuCategoryDTO) {
        cStockMapper.updateSkuCategory(skuCategoryDTO);
    }

    @Override
    public skuCategoryDTO getSkuCateogyBYSKU(String sku) {
        return cStockMapper.getSkuCategoryBySKU(sku);
    }

    @Override
    public List<PUMIDTO> getPUMIAll() {
        return cStockMapper.getPUMIAll();
    }

    @Override
    public List<PUMIDTO> getPUMIByDate(String date) {
        return cStockMapper.getPUMIByDate(date);
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
