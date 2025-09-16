package com.ecom.mapper.mysql;

import com.ecom.pojo.dto.PUMIDTO;
import com.ecom.pojo.dto.skuCategoryDTO;
import com.ecom.pojo.entity.MinCStock;
import com.ecom.pojo.vo.MinCStockSummaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CStockMapper {


    List<MinCStock> getMinQty();

    void truncateTable(String tableName);

    void updateStgTable(String path, int skip,@Param("tableName") String tableName);


    void insertNERP();

    void insertSynapse();

    void insertSkuCategory(@Param("skuCategoryDTOs") List<skuCategoryDTO> skuCategoryDTOS);

    @Select("select * from sku_category")
    List<skuCategoryDTO> getSkuCategory();

    @Update("delete from sku_category where sku = #{sku};")
    void delSkuCategory(String sku);

    @Update("update sku_category set sku = #{sku},category = #{category},description = #{description} where sku = #{sku};")
    void updateSkuCategory(skuCategoryDTO skuCategoryDTO);

    @Select("select * from sku_category where sku = #{sku}")
    skuCategoryDTO getSkuCategoryBySKU(String sku);

    List<MinCStockSummaryVO> getMinQtySummary();

    @Select("select * from pumi_history")
    List<PUMIDTO> getPUMIAll();

    @Select("select * from pumi_history where pumi_date = #{date}")
    List<PUMIDTO> getPUMIByDate(String date);
}
