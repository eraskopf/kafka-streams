package com.example.kafkastreams.dto.priceapi.priceitem;

import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {
    private LastModifiedBy lastModifiedBy;
    private String priceListType;
    private String status;
    private String salesFromDate;
    private String toDate;
    private String productType;
    private Boolean dollarList;
    private String fromDate;
    private String salesToDate;
    private String mediaType;
    private String id;

    public static PriceList from(PriceItemSalesForceDTO.PriceListSalesForce pls) {
        return PriceList.builder()
                .id(pls.getId())
                .lastModifiedBy(LastModifiedBy.from(pls.getLastModifiedBySalesForce()))
                .priceListType(pls.getPriceListTypeC())
                .status(pls.getStatusC())
                .salesFromDate(pls.getSalesFromDateC())
                .toDate(pls.getToDateC())
                .productType(pls.getProductTypeC())
                .dollarList(pls.getDollarListC())
                .fromDate(pls.getFromDateC())
                .salesToDate(pls.getSalesToDateC())
                .mediaType(pls.getMediaTypeC())
                .build();
    }
}