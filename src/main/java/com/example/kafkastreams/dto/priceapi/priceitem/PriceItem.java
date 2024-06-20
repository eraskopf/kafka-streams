package com.example.kafkastreams.dto.priceapi.priceitem;

import com.example.kafkastreams.dto.salesforce.priceitem.PriceItemSalesForceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceItem {
    private PriceList priceList;
    private String id;
    private String channel;
    private String locationInitial;
    private Integer spotLength;
    private String fromDate;
    private String unit;
    private String toDate;
    private String mediaTypeList;
    private String locationText;
    private String formatInitial;
    private String priceFormat;
    private Boolean contentFormat;
    private String currency;
    private String format;
    private Integer pricePerUnit;
    private String fromTime;
    private String dayOfWeek;
    private String salesToDate;
    private String channelInitial;
    private String name;
    private Boolean status;
    private String productInitial;
    private String product;
    private String genre;
    private String salesFromDate;

    public static PriceItem from(PriceItemSalesForceDTO.PriceItemSalesForce pis) {
        return PriceItem.builder()
                .id(pis.getId())
                .priceList(PriceList.from(pis.getPriceListSalesForce()))
                .channel(pis.getChannelC())
                .locationInitial(pis.getLocationInitialC())
                .spotLength(pis.getSpotLengthC())
                .fromDate(pis.getFromDateC())
                .unit(pis.getUnitC())
                .toDate(pis.getToDateC())
                .mediaTypeList(pis.getMediaTypeListC())
                .locationText(pis.getLocationTextC())
                .formatInitial(pis.getFormatInitialC())
                .priceFormat(pis.getPriceFormatC())
                .contentFormat(pis.getContentFormatC())
                .currency(pis.getCurrencyC())
                .format(pis.getFormatC())
                .pricePerUnit(pis.getPricePerUnitC())
                .fromTime(pis.getFromTimeC())
                .dayOfWeek(pis.getDayOfWeekC())
                .salesToDate(pis.getSalesToDateC())
                .channelInitial(pis.getChannelInitialC())
                .name(pis.getName())
                .status(pis.getStatusC())
                .productInitial(pis.getProductInitialC())
                .product(pis.getProductC())
                .genre(pis.getGenreC())
                .salesFromDate(pis.getSalesFromDateC())
                .build();
    }
}