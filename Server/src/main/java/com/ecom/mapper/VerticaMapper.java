package com.ecom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VerticaMapper {

    @Select("select rdo_id from edw.fact_returns_v4 where return_shipped_ts > '2024-06-10' and return_shipped_ts < '2024-06-13' and is_active = 1 and return_tracking_id is not null and rdo_id is not null")
    List<String> test();
}
