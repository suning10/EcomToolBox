package com.ecom.mapper.mysql;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.pojo.entity.ReturnSimple;
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

    List<ReturnSimple> searchByRDOSimple(SearchByRDODTO searchByRDODTO);

    void updateStgTable();

    void loadDataInlineStg(String path);

    @Update("INSERT INTO return_search_simple SELECT  * from stg_return_search_simple on duplicate key UPDATE return_search_simple.tracking_number = stg_return_search_simple.tracking_number;")
    void loadDataReturnSearch();
}
