package com.example.kafkastreams.dto.salesforce.priceitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceItemSalesForceDTO {
    @JsonProperty("PriceItem__c")
    private PriceItemSalesForce priceItemSalesForce;

    @Data
    public static class PriceItemSalesForce {
        @JsonProperty("Price_List__r")
        private PriceListSalesForce priceListSalesForce;
        @JsonProperty("Id")
        private String id;
        @JsonProperty("Channel__c")
        private String channelC;
        @JsonProperty("Location_Initial__c")
        private String locationInitialC;
        @JsonProperty("Spot_Lenght__c")
        private Integer spotLengthC;
        @JsonProperty("From_Date__c")
        private String fromDateC;
        @JsonProperty("Unit__c")
        private String unitC;
        @JsonProperty("To_Date__c")
        private String toDateC;
        @JsonProperty("Media_Type_List__c")
        private String mediaTypeListC;
        @JsonProperty("Location_Text__c")
        private String locationTextC;
        @JsonProperty("Format_Initial__c")
        private String formatInitialC;
        @JsonProperty("Price_Format__c")
        private String priceFormatC;
        @JsonProperty("Content_Format__c")
        private Boolean contentFormatC;
        @JsonProperty("Currency__c")
        private String currencyC;
        @JsonProperty("Format__c")
        private String formatC;
        @JsonProperty("Price_Per_Unit__c")
        private Integer pricePerUnitC;
        @JsonProperty("From_Time__c")
        private String fromTimeC;
        @JsonProperty("Day_of_the_week__c")
        private String dayOfWeekC;
        @JsonProperty("Sales_ToDate__c")
        private String salesToDateC;
        @JsonProperty("Channel_Initial__c")
        private String channelInitialC;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Status__c")
        private Boolean statusC;
        @JsonProperty("Product_Initial__c")
        private String productInitialC;
        @JsonProperty("Product__c")
        private String productC;
        @JsonProperty("Price_List__c")
        private String priceListC;
        @JsonProperty("Genre__c")
        private String genreC;
        @JsonProperty("Sales_FromDate__c")
        private String salesFromDateC;
    }

    @Data
    public static class PriceListSalesForce {
        @JsonProperty("LastModifiedBy")
        private LastModifiedBySalesForce lastModifiedBySalesForce;
        @JsonProperty("LastModifiedById")
        private String lastModifiedById;
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
    }

    @Data
    public static class LastModifiedBySalesForce {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Id")
        private String id;
    }
}
