package com.plexus.prices.converter;

import com.plexus.prices.models.dto.PriceDto;
import com.plexus.prices.models.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author victor.martingil
 **/
@ExtendWith(MockitoExtension.class)
class PriceConverterTest {

    @InjectMocks
    PriceConverter priceConverter;

    @Test
    void convertEntityToDto() {
        //given
        Long id = 1L;
        int brandId = 1;
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 18, 30, 0);
        LocalDateTime queryDate = LocalDateTime.of(2020, 6, 14, 16, 00, 0);
        int priceList = 2;
        int productId = 35455;
        int priority = 1;
        double price = 25.45;
        String curr = "EUR";

        PriceEntity priceEntity = new PriceEntity(id, brandId, startDate, endDate, priceList, productId, priority, price, curr);

        //when
        PriceDto priceDto = priceConverter.convertEntityToDto(priceEntity);

        //then
        assertNotNull(priceDto);
        assertEquals(priceDto.getPrice(), price);
    }

}