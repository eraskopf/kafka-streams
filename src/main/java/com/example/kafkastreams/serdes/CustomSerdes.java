package com.example.kafkastreams.serdes;

import com.example.kafkastreams.dto.salesforce.MessageDto;
import com.example.kafkastreams.dto.salesforce.adformat.AdFormatSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import com.example.kafkastreams.dto.salesforce.pricingindexitem.PricingIndexItemSalesForceDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public final class CustomSerdes {

    //https://kafka.apache.org/31/documentation/streams/developer-guide/datatypes
    public static Serde<ArrayList> MessageList() {
        JsonSerializer<ArrayList> serializer = new JsonSerializer<>();
        JsonDeserializer<ArrayList> deserializer = new JsonDeserializer<>(ArrayList.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
    public static Serde<MessageDto> MessageDto() {
        JsonSerializer<MessageDto> serializer = new JsonSerializer<>();
        JsonDeserializer<MessageDto> deserializer = new JsonDeserializer<>(MessageDto.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
    public static Serde<List<PriceItemSalesForceDTO>> PriceItemSalesForceDTO() {
        JsonSerializer<List<PriceItemSalesForceDTO>> serializer = new JsonSerializer<>();
        JsonDeserializer<List<PriceItemSalesForceDTO>> deserializer = new JsonDeserializer<>(new TypeReference<>() {
        });
        return Serdes.serdeFrom(serializer, deserializer);
    }
    public static Serde<List<PricingIndexItemSalesForceDTO>> PricingIndexItemSalesForceDTO() {
        JsonSerializer<List<PricingIndexItemSalesForceDTO>> serializer = new JsonSerializer<>();
        JsonDeserializer<List<PricingIndexItemSalesForceDTO>> deserializer = new JsonDeserializer<>(new TypeReference<>() {
        });
        return Serdes.serdeFrom(serializer, deserializer);
    }
    public static Serde<AdFormatSalesForceDTO> AdFormatSalesForceDTO() {
        JsonSerializer<AdFormatSalesForceDTO> serializer = new JsonSerializer<>();
        JsonDeserializer<AdFormatSalesForceDTO> deserializer = new JsonDeserializer<>(new TypeReference<>() {
        });
        return Serdes.serdeFrom(serializer, deserializer);
    }
}