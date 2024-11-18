package com.ecom.common.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ecom.executable")
@Data
public class ExecutableProperties {
    private String path;

}
