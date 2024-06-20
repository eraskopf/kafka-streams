package com.example.kafkastreams.dto.priceapi.pricingindexitem;

import com.example.kafkastreams.dto.priceapi.priceitem.PriceItem;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.pricingindexitem.PricingIndexItemSalesForceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingIndexItemGloboApiDTO {
    private PricingIndexItem pricingIndexItem;

    public static PricingIndexItemGloboApiDTO from(PricingIndexItemSalesForceDTO dto) {
        return PricingIndexItemGloboApiDTO.builder()
                .pricingIndexItem(PricingIndexItem.from(dto.getPricingIndexItem()))
                .build();
    }
}
