package com.ecom.service;

import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.dto.skuCategoryDTO;
import com.ecom.pojo.entity.MinCStock;
import com.ecom.pojo.vo.MinCStockSummaryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CStockService {
    List<MinCStock> getMinQtyCStock();

    List<MinCStockSummaryVO> getMinQtyCStockSummary();

    void uploadPUMI(List<PUMIDTO> pumidtos);

    void uploadSKUCategory(@Param("skuCategoryDTOS")List<skuCategoryDTO> skuCategoryDTOS);

    List<skuCategoryDTO> getSkuCateogy();

    void delSkuCateogy(String sku);

    void updateSkuCateogy(skuCategoryDTO skuCategoryDTO);

    skuCategoryDTO getSkuCateogyBYSKU(String sku);

    List<PUMIDTO> getPUMIAll();

    List<PUMIDTO> getPUMIByDate(String date);
}
