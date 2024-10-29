package com.ecom.service;


import com.ecom.common.enumeration.UploadStatus;
import com.ecom.pojo.dto.SKUSearchDTO;
import com.ecom.pojo.entity.*;
import com.ecom.pojo.vo.SKUSummaryByMvmTypeVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SCRService {
    List<SKUComparison> querySKUComparison(String sku, String start, String end, String sloc);

    List<SKUActivityNERP> querySKUDetailNERPAdj(SKUSearchDTO skuSearchDTO);

    List<SKUActivitySynapse> querySKUDetailSynapseAdj(SKUSearchDTO skuSearchDTO);

    SKUSummaryByMvmTypeVO querySKUSummarySynapseAdj(SKUSearchDTO skuSearchDTO);

    SKUSummaryByMvmTypeVO querySKUSummaryNERPAdj(SKUSearchDTO skuSearchDTO);

    List<SKUActivityNERP> querySKUComparisonMissingNerp(String sku, String start, String end, String sloc);

    List<SKUActivitySynapse> querySKUComparisonMissingSynapse(String sku, String start, String end, String sloc);

    List<ScrReportSummary> queryScrReportSummary(String date);

    List<ScrReportGap> queryScrReportGap(String date, String sloc);

    List<ScrReportRaw> queryScrReportDetail(String date, String sloc);

    List<ScrReportDod> queryScrReportDOD(String sloc);

    UploadStatus uploadSCRReport(MultipartFile file);

    UploadStatus uploadSkuMB51(MultipartFile file);

    UploadStatus uploadSkuItemActivity(MultipartFile file);
}
