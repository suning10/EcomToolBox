package com.ecom.service;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.pojo.entity.ReturnSimple;
import com.ecom.pojo.vo.agedReturnDashboardVO;
import com.ecom.pojo.vo.agedReturnSummaryVO;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ReturnSearchService {


    List<Return> getByRDO(SearchByRDODTO searchByRDODTO);

    List<Return> getByPO(SearchByRDODTO searchByRDODTO);

    void upload(MultipartFile file);

    List<ReturnSimple> getByRDOSimple(SearchByRDODTO searchByRDODTO);

    void uploadSimple(MultipartFile file);


    List<agedReturnDashboardVO> getAgedReturnDashboard(boolean flagCache) throws NoSuchAlgorithmException;

    boolean uploadAgedReturnNerp();

    public List<agedReturnSummaryVO> getAgedReturnSummary(List<agedReturnDashboardVO> raw);
}
