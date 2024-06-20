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
public class PriceItemGloboApiDTO {
    private PriceItem priceItem;

    public static PriceItemGloboApiDTO from(PriceItemSalesForceDTO dto) {
        return PriceItemGloboApiDTO.builder()
                .priceItem(PriceItem.from(dto.getPriceItemSalesForce()))
                .build();
    }
}
