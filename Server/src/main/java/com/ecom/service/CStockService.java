package com.ecom.service;

import com.ecom.pojo.vo.MinCStockVO;

import java.util.List;

public interface CStockService {
    List<MinCStockVO> getMinQtyCStock();
}
