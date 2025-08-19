package com.ecom.mapper.mysql;

import com.ecom.pojo.entity.MinCStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CStockMapper {


    List<MinCStock> getMinQty();

    void truncateTable(String tableName);

    void updateStgTable(String path, int skip,@Param("tableName") String tableName);


    void insertNERP();

    void insertSynapse();
}
