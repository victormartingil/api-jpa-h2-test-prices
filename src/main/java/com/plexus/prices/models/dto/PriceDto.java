package com.plexus.prices.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO of Prices
 *
 * @author victor.martingil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDto {

    private Long id;
    private int productId;
    private int brandId;
    private int priceList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm.ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm.ss")
    private LocalDateTime endDate;
    private double price;

}
