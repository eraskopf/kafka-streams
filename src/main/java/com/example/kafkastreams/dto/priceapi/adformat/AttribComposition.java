package com.example.kafkastreams.dto.priceapi.adformat;

import com.example.kafkastreams.dto.priceapi.priceitem.PriceList;
import com.example.kafkastreams.dto.salesforce.adformat.AdFormatSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttribComposition {

    private String name;

    public static AttribComposition from(AdFormatSalesForceDTO.AttribComposition dto) {
        return AttribComposition.builder()
                .name(dto.getName())
                .build();
    }
}