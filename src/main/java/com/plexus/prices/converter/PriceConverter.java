package com.plexus.prices.converter;

import com.plexus.prices.models.dto.PriceDto;
import com.plexus.prices.models.entity.PriceEntity;
import org.springframework.stereotype.Component;

/**
 * Converter between PricesEntity and PricesDto
 *
 * @author victor.martingil
 */
@Component
public class PriceConverter {

    /**
     *
     * @param priceEntity
     * @return priceDto
     */
    public PriceDto convertEntityToDto(PriceEntity priceEntity) {

        return PriceDto.builder()
                .id(priceEntity.getId())
                .productId(priceEntity.getProductId())
                .brandId(priceEntity.getBrandId())
                .priceList(priceEntity.getPriceList())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .price(priceEntity.getPrice())
                .build();
    }

}
