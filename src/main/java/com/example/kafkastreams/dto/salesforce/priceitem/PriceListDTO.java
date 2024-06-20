package com.example.kafkastreams.dto.salesforce.priceitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListDTO implements Serializable {


    @Builder.Default
    private LocalDateTime dateTimeProcess = LocalDateTime.now();

    @JsonProperty("Price_List_Type__c")
    private String priceListTypeC;
    @JsonProperty("Status__c")
    private String statusC;
    @JsonProperty("Sales_FromDate__c")
    private String salesFromDateC;
    @JsonProperty("To_Date__c")
    private String toDateC;
    @JsonProperty("ProductType__c")
    private String productTypeC;
    @JsonProperty("Dollar_List__c")
    private Boolean dollarListC;
    @JsonProperty("from_Date__c")
    private String fromDateC;
    @JsonProperty("Sales_ToDate__c")
    private String salesToDateC;
    @JsonProperty("MediaType__c")
    private String mediaTypeC;
    @JsonProperty("Id")
    private String id;

    public static PriceListDTO from(PriceItemSalesForceDTO.PriceListSalesForce priceListSalesForce) {
        return PriceListDTO.builder().id(priceListSalesForce.getId())
                .priceListTypeC(priceListSalesForce.getPriceListTypeC())
                .build();
    }

}
