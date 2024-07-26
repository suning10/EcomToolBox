package com.ecom.mapper.vertica;

import com.ecom.pojo.entity.FedExTracking;
import com.ecom.pojo.entity.PBTracking;
import com.ecom.pojo.entity.UPSTracking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VerticaMapper {

    @Select("select rdo_id from edw.fact_returns_v4 where return_shipped_ts > '2024-06-10' and return_shipped_ts < '2024-06-13' and is_active = 1 and return_tracking_id is not null and rdo_id is not null")
    List<String> test();


    List<String> test2();

    List<PBTracking> queryByDeliveryDatePB(@Param("start") String start,@Param("end") String end);

    List<PBTracking> queryByShipPB(@Param("start") String start, @Param("end") String end);

    int queryByDeliveryDatePBSummary(String start, String end);

    int queryByShipDatePBSummary(String start, String end);

    List<UPSTracking> queryUPSTracking(List<String> trackingIds, int isActive);

    List<FedExTracking> queryFedExTracking(@Param("trackingIds") List<String> lowerCaseTrackingIds, @Param("isActive")int isActive);
}
