package com.ecom.service;

import com.ecom.pojo.entity.KPI;

import java.util.List;

public interface KpiService {


    List<KPI> getKPISummary(String date);

    void insertKPI(KPI kpi);
}
