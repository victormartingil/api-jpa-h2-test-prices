package com.plexus.prices.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author victor.martingil
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceQuery {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime date;
    private int productId;
    private int brandId;

}
