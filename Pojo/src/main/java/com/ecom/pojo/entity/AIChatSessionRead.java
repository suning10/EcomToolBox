package com.ecom.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIChatSessionRead {

    private String id;
    @Builder.Default
    private String title = "";
    private String created_at;
    private String updated_at;
    private int message_count;

}
