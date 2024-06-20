package com.example.kafkastreams.dto.priceapi.adformat;

import com.example.kafkastreams.dto.priceapi.priceitem.PriceList;
import com.example.kafkastreams.dto.salesforce.adformat.AdFormatSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdFormat {
    private String id;
    private Boolean mediaTypePayTv;
    private Boolean mediaTypeFreeToAir;
    private String name;
    private String code;
    private String status;
    private Boolean mediaTypeDigital;
    private Boolean aggregateAudience;
    private String pricingIndexTable;
    private String businessKeyPayTV;
    private String businessKeyFreeToAir;
    private String referenceFormatInitial;
    private String format;
    private String composicaoFormula;

    public static AdFormat from(AdFormatSalesForceDTO.AdFormat dto) {
        return AdFormat.builder()
                .id(dto.getId())
                .mediaTypePayTv(dto.getTipoMidiaPayTvC())
                .mediaTypeFreeToAir(dto.getTipoMidiaFreeToAirC())
                .mediaTypeDigital(dto.getTipoMidiaDigitalC())
                .name(dto.getName())
                .code(dto.getSiglaC())
                .status(dto.getStatusC())
                .aggregateAudience(dto.getAggregateAudienceC())
                .pricingIndexTable(dto.getPricingIndexTableC())
                .businessKeyPayTV(dto.getChaveNegocioPayTVC())
                .businessKeyFreeToAir(dto.getChaveNegocioFreeToAirC())
                .referenceFormatInitial(dto.getReferenceFormatInitialC())
                .format(dto.getFormatC())
                .composicaoFormula(dto.getComposicaoFormulaC())
                .build();
    }
}