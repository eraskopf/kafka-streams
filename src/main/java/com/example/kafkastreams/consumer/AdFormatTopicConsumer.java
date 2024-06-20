package com.example.kafkastreams.consumer;

import com.example.kafkastreams.config.KafkaConfiguration;
import com.example.kafkastreams.dto.priceapi.adformat.AdFormatGloboApiDTO;
import com.example.kafkastreams.dto.salesforce.adformat.AdFormatSalesForceDTO;
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

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdFormatTopicConsumer {

    private final KafkaConfiguration kafkaConfiguration;

    @Bean
    public KStream<String, AdFormatSalesForceDTO> kStreamAdFormat(StreamsBuilder kStreamBuilder) {
        KStream<String, AdFormatSalesForceDTO> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicAdFormat(), Consumed.with(Serdes.String(), CustomSerdes.AdFormatSalesForceDTO()));
        Duration windowSize = Duration.ofSeconds(5);
        Duration gracePeriod = Duration.ofSeconds(2);
        SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);

        kStream.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.AdFormatSalesForceDTO()))
                .windowedBy(sessionWindow)
                .aggregate(ArrayList::new,
                        (key, value, aggregate1) -> {
                            log.info("preparing record received {}", value);

                            if (nonNull(value)) {
                                aggregate1.add(AdFormatGloboApiDTO.from(value));
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
                .to(kafkaConfiguration.getOutputTopicAdFormat(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));

        return kStream;
    }
}