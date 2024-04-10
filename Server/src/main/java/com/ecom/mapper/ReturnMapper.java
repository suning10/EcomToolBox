package com.ecom.mapper;

import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReturnMapper {


    List<Return> searchByRDO(SearchByRDODTO searchByRDODTO);

    @Update("create Temporary table rdoList (rdo varchar(30))")
    void createTempRDOTable();

    void insertrdoList (List<String> rdos);
}
