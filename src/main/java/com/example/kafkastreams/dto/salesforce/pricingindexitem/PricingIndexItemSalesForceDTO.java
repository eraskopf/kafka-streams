package com.example.kafkastreams.dto.salesforce.pricingindexitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PricingIndexItemSalesForceDTO {
    @JsonProperty("Pricing_Index_Item__c")
    private PricingIndexItem pricingIndexItem;

    @Data
    public static class PricingIndexItem {
        @JsonProperty("Id")
        private String id;
        @JsonProperty("Aggregate_Audience__c")
        private Boolean aggregateAudienceC;
        @JsonProperty("Sales_FromDate__c")
        private String salesFromDateC;
        @JsonProperty("Tipo_de_Midia__c")
        private String tipoMidiaC;
        @JsonProperty("Location_Initial__c")
        private String locationInitialC;
        @JsonProperty("Multiplier__c")
        private Double multiplierC;
        @JsonProperty("Selling_Type__c")
        private String sellingTypeC;
        @JsonProperty("Sales_ToDate__c")
        private String salesToDateC;
        @JsonProperty("Spot_Lenght__c")
        private Integer spotLenghtC;
        @JsonProperty("Pricing_Index_Table__c")
        private String pricingIndexTableC;
        @JsonProperty("Program_Initial__c")
        private String programInitialC;
        @JsonProperty("Inicio_Vigencia_Indice__c")
        private String inicioVigenciaIndiceC;
        @JsonProperty("Fim_Vigencia_Indice__c")
        private String fimVigenciaIndiceC;
    }
}
