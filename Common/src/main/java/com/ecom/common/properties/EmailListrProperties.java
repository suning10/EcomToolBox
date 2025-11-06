package com.ecom.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ecom.email-list")
@Component
@Data
public class EmailListrProperties {

    private String[] cstock;

//    //public String[] getCStockEmailList(){
//        return this.cstock.split(",");
//    }

}
