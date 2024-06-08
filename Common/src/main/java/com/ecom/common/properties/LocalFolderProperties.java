package com.ecom.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ecom.local-upload-folder")
@Component
@Data
public class LocalFolderProperties {

    private String path;

}
