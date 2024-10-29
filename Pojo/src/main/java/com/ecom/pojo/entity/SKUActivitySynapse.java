package com.ecom.pojo.entity;

        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SKUActivitySynapse  implements Serializable {

    private String item;
    private String reference;
    private String slot;
    private String orderId;
    private String originShipName;
    private String lot;
    private String po;
    private String inventoryStatus;
    private String qty;
    private String transactionType;
    private String transactionDate;
}