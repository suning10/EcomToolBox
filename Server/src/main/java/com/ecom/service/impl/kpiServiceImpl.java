package com.ecom.service.impl;

import com.ecom.pojo.entity.KPI;
import com.ecom.service.KpiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class kpiServiceImpl implements KpiService {
    @Override
    public List<KPI> getKPISummary(String date) {
        return null;
    }

    @Override
    public void insertKPI(KPI kpi) {

    }
}
