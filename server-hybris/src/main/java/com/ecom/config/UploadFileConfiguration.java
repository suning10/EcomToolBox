package com.ecom.config;


import com.ecom.common.properties.LocalFolderProperties;
import com.ecom.common.utils.LocalFolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class UploadFileConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LocalFolderUtil localFolderUtil (LocalFolderProperties localFolderProperties){
        log.info("start creating Local folder util");
        log.info("current local folder path is {}", localFolderProperties.getPath());
        return new LocalFolderUtil(localFolderProperties.getPath());
    }
}
