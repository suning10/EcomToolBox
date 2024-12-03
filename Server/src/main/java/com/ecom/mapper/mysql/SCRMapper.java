package com.ecom.mapper.mysql;

import com.ecom.pojo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SCRMapper {


    List<SKUComparison> querySKUComparison(@Param("sku") String sku, @Param("start") String start, @Param("end") String end, @Param("sloc") String sloc);

    List<SKUActivityNERP> postSKUDetailNERPAdj(String sku, String start, String end, String sloc);

    List<SKUActivitySynapse> postSKUDetailSynapseAdj(String sku, String start, String end, String sloc);


    List<SKUSummaryByMvmType> postSKUSummarySynapseAdj(String sku, String start, String end, String sloc);

    List<SKUSummaryByMvmType> postSKUSummaryNERPAdj(String sku, String start, String end, String sloc);

    List<SKUActivityNERP> postSKUComparisonMissingNerp(String sku, String start, String end, String sloc);

    List<SKUActivitySynapse> postSKUComparisonMissingSynapse(String sku, String start, String end, String sloc);

    List<ScrReportSummary> getSCRReportSummary(String date);

    List<ScrReportGap> getSCRReportGap(@Param("date")String date, @Param("sloc")String sloc);

    List<ScrReportGap> getSCRReportGapWR2E(String date, String wr2E);

    List<ScrReportRaw> getSCRReportDetailWR2E(String date, String wr2E);

    List<ScrReportRaw> getSCRReportDetil(String date, String sloc);

    List<ScrReportDod> getSCRReportDOD(String sloc);

    void insert();

    void updateStgTable(String path, int skip,@Param("tableName") String tableName);

    void truncateTable(@Param("tableName") String tableName);

    void insertMB51();

    void insertItemActivity();

    void updateComma(@Param("tableName") String tableName);

    String getStgDate(@Param("tableName") String tableName , @Param("dateName") String dateName);

    int getTableDate(@Param("tableName") String tableName , @Param("dateName") String dateName, String stgDate);

    List<SKUActivitySynapse> postSKUComparisonMissingSynapseWOSLoc(String sku, String start, String end);

    List<SKUActivityNERP> postSKUComparisonMissingNerpWOSLoc(String sku, String start, String end);

    List<SKUComparison> querySKUComparisonAll(String sku, String start, String end, String sloc);

    List<SKUActivityNERP> postSKUComparisonMissingNerpEmail(String start, String end);

    List<SKUActivitySynapse> postSKUComparisonMissingSynapseEmail(String start, String end);

    List<DODSearch> getDodBySku(String material, String sloc);
}
