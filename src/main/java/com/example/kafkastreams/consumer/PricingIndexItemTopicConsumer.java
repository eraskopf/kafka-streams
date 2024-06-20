package com.example.kafkastreams.consumer;

import com.example.kafkastreams.config.KafkaConfiguration;
import com.example.kafkastreams.dto.priceapi.pricingindexitem.PricingIndexItemGloboApiDTO;
import com.example.kafkastreams.dto.salesforce.pricingindexitem.PricingIndexItemSalesForceDTO;
import com.example.kafkastreams.serdes.CustomSerdes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class PricingIndexItemTopicConsumer {

    private final KafkaConfiguration kafkaConfiguration;

    @Bean
    public KStream<String, List<PricingIndexItemSalesForceDTO>> kStreamPricingIndexItem(StreamsBuilder kStreamBuilder) {
        KStream<String, List<PricingIndexItemSalesForceDTO>> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicPricingIndexItem(), Consumed.with(Serdes.String(), CustomSerdes.PricingIndexItemSalesForceDTO()));
        Duration windowSize = Duration.ofSeconds(10);
        Duration gracePeriod = Duration.ofSeconds(5);
        SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);

        kStream.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.PricingIndexItemSalesForceDTO()))
                .windowedBy(sessionWindow)
                .aggregate(ArrayList::new,
                        (key, value, aggregate1) -> {
                            log.info("preparing record received {}", value);

                            if (nonNull(value)) {
                                value.forEach(
                                        pl -> aggregate1.add(PricingIndexItemGloboApiDTO.from(pl))
                                );
                            }

                            return aggregate1;
                        },
                        (key, aggregate1, aggregate2) -> {
                            aggregate1.addAll(aggregate2);
                            return aggregate1;
                        },
//                        (key, aggregate1, aggregate2) -> { return aggregate1; },
                        Materialized.with(Serdes.String(), CustomSerdes.MessageList()))
                .toStream()
                .map((key, value) -> new KeyValue<>(key.key(), value))
                .to(kafkaConfiguration.getOutputTopicPricingIndexItem(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));

        return kStream;
    }
}