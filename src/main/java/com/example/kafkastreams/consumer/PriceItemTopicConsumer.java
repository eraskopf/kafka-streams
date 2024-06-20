package com.example.kafkastreams.consumer;

import com.example.kafkastreams.config.KafkaConfiguration;
import com.example.kafkastreams.dto.priceapi.priceitem.PriceItemGloboApiDTO;
import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import com.example.kafkastreams.serdes.CustomSerdes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceItemTopicConsumer {

    private final KafkaConfiguration kafkaConfiguration;

    //https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#streams-spring
    @Bean
    public KStream<String, List<PriceItemSalesForceDTO>> kStreamPriceItem(StreamsBuilder kStreamBuilder) {
        KStream<String, List<PriceItemSalesForceDTO>> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicPriceItem(), Consumed.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()));
        KStream<String, List<PriceItemSalesForceDTO>> streamToPriceList = kStreamBuilder.stream(kafkaConfiguration.getInputTopicPriceItem(), Consumed.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()));
        Duration windowSize = Duration.ofSeconds(10);
        Duration gracePeriod = Duration.ofSeconds(5);
        SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);

//        kStream.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()))
//                .windowedBy(sessionWindow)
//                .aggregate(ArrayList::new,
//                        (key, value, aggregate1) -> {
//                            log.info("preparing record received {}", value);
//
//                            if (nonNull(value)) {
//                                value.forEach(
//                                        pi -> {
//                                            //for (int i = 0; i < 1000; i++) {
//                                            aggregate1.add(pi);
//                                            //}
//                                        }
//                                );
//                            }
//
//                            return aggregate1;
//                        },
//                        (key, aggregate1, aggregate2) -> {
//                            aggregate1.addAll(aggregate2);
//                            return aggregate1;
//                        },
////                        (key, aggregate1, aggregate2) -> { return aggregate1; },
//                        Materialized.with(Serdes.String(), CustomSerdes.MessageList()))
//                .toStream()
//                .map((key, value) -> new KeyValue<>(key.key(), value))
//                .to(kafkaConfiguration.getOutputTopicPriceItem(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));

        streamToPriceList.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()))
                .windowedBy(sessionWindow)
                .aggregate(ArrayList::new,
                        (key, value, aggregate1) -> {
                            log.info("preparing record received {}", value);

                            if (nonNull(value)) {
                                value.forEach(
                                        pl -> aggregate1.add(PriceItemGloboApiDTO.from(pl))
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
                .to(kafkaConfiguration.getOutputTopicPriceItem(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));

        return streamToPriceList;
    }
}