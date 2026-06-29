package com.ecom.mapper;

import com.ecom.pojo.dto.HybrisReturnDTO;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.ReturnSimple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReturnHybrisMapper {

    void createTempRDOTable();
    void truncateTempRDOTable();
    void insertrdoList (List<String> rdos);
    void loadDataInline(String path);
    void updateReturnMaster();
    void updateStgTable(String path, int skip,@Param("tableName") String tableName);
    List<HybrisReturnDTO> getHybrisReturn();

}
