package com.example.kafkastreams.dto.priceapi.pricingindexitem;

import com.example.kafkastreams.dto.priceapi.priceitem.PriceItem;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.pricingindexitem.PricingIndexItemSalesForceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingIndexItem {
    private String id;
    private Boolean aggregateAudience;
    private String salesFromDate;
    private String mediaType;
    private String locationInitial;
    private Double multiplier;
    private String sellingType;
    private String salesToDate;
    private Integer spotLenght;
    private String pricingIndexTable;
    private String programInitial;
    private String startIndexDate;
    private String endIndexDate;

    public static PricingIndexItem from(PricingIndexItemSalesForceDTO.PricingIndexItem pii) {
        return PricingIndexItem.builder()
                .id(pii.getId())
                .aggregateAudience(pii.getAggregateAudienceC())
                .salesFromDate(pii.getSalesFromDateC())
                .mediaType(pii.getTipoMidiaC())
                .locationInitial(pii.getLocationInitialC())
                .multiplier(pii.getMultiplierC())
                .sellingType(pii.getSellingTypeC())
                .salesToDate(pii.getSalesToDateC())
                .spotLenght(pii.getSpotLenghtC())
                .pricingIndexTable(pii.getPricingIndexTableC())
                .programInitial(pii.getProgramInitialC())
                .startIndexDate(pii.getInicioVigenciaIndiceC())
                .endIndexDate(pii.getFimVigenciaIndiceC())
                .build();
    }
}