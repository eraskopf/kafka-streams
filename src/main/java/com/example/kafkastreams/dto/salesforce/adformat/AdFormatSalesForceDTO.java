package com.example.kafkastreams.dto.salesforce.adformat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdFormatSalesForceDTO {
    @JsonProperty("Ad_Format__c")
    private AdFormat adFormat;

    @Data
    public static class AttribComposition {
        @JsonProperty("Name")
        private String name;
    }
    @Data
    public static class AtribCaracterizacao {
        @JsonProperty("Name")
        private String name;
    }

    @Data
    public static class AdFormat {
        @JsonProperty("Id")
        private String id;
        @JsonProperty("Tipo_Midia_PayTV__c")
        private Boolean tipoMidiaPayTvC;
        @JsonProperty("Tipo_Midia_Free_To_Air__c")
        private Boolean tipoMidiaFreeToAirC;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Atrib_Caracterizacao__c")
        private String atribCaracterizacaoC;
        @JsonProperty("Attrib_Composition__r")
        private AttribComposition attribCompositionR;
        @JsonProperty("Atrib_Caracterizacao__r")
        private AtribCaracterizacao atribCaracterizacao;
        @JsonProperty("Sigla__c")
        private String siglaC;
        @JsonProperty("Status__c")
        private String statusC;
        @JsonProperty("Nome_Caracterizacao__c")
        private String nomeCaracterizacaoC;
        @JsonProperty("Tipo_Midia_Digital__c")
        private Boolean tipoMidiaDigitalC;
        @JsonProperty("Aggregate_audience__c")
        private Boolean aggregateAudienceC;
        @JsonProperty("Pricing_Index_Table__c")
        private String pricingIndexTableC;
        @JsonProperty("Attrib_Composition__c")
        private String attribCompositionC;
        @JsonProperty("Chave_Negocio_PayTV__c")
        private String chaveNegocioPayTVC;
        @JsonProperty("Chave_Negocio_Free_To_Air__c")
        private String chaveNegocioFreeToAirC;
        @JsonProperty("ReferenceFormat_Initial__c")
        private String referenceFormatInitialC;
        @JsonProperty("Format__c")
        private String formatC;
        @JsonProperty("Composicao_formula__c")
        private String composicaoFormulaC;


    }
}
