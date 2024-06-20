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
public class LastModifiedBy {
    private String name;
    private String id;

    public static LastModifiedBy from(PriceItemSalesForceDTO.LastModifiedBySalesForce last) {
        return LastModifiedBy.builder().id(last.getId()).name(last.getName()).build();
    }
}