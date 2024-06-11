package com.ecom.mapper;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReturnMapper {


    List<Return> searchByRDO(SearchByRDODTO searchByRDODTO);


    void createTempRDOTable();

    void truncateTempRDOTable();

    void insertrdoList (List<String> rdos);

    List<Return> searchByPO(SearchByRDODTO searchByRDODTO);

    void loadDataInline(String path);

    void updateReturnMaster();
}
