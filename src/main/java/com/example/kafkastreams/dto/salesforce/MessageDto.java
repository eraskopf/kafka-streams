package com.example.kafkastreams.dto.salesforce;

import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Serializable {

    List<PriceItemSalesForceDTO> priceItensSalesForce;

}
