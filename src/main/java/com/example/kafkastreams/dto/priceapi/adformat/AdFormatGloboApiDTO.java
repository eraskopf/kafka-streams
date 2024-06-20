package com.example.kafkastreams.dto.priceapi.adformat;

import com.example.kafkastreams.dto.priceapi.priceitem.PriceItem;
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
public class AdFormatGloboApiDTO {
    private AdFormat adFormat;

    public static AdFormatGloboApiDTO from(AdFormatSalesForceDTO dto) {
        return AdFormatGloboApiDTO.builder()
                .adFormat(AdFormat.from(dto.getAdFormat()))
                .build();
    }
}
