package com.ecom.service;

import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.vo.MinCStockSummaryVO;
import com.ecom.pojo.vo.MinCStockVO;

import java.util.List;

public interface CStockService {
    List<MinCStockVO> getMinQtyCStock();

    List<MinCStockSummaryVO> getMinQtyCStockSummary();

    void uploadPUMI(List<PUMIDTO> pumidtos);
}
