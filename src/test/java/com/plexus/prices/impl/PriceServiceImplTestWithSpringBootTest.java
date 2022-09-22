package com.plexus.prices.impl;

import com.plexus.prices.models.dto.PriceResponseDto;
import com.plexus.prices.models.entity.PriceEntity;
import com.plexus.prices.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PriceServiceImplTestWithSpringBootTest {

    @Autowired
    PriceServiceImpl service;

    @Test
    void getPrice() {
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
        List<PriceEntity> priceEntityList = new ArrayList<>();
        priceEntityList.add(priceEntity);
        PriceResponseDto priceResponseDto = new PriceResponseDto(id, productId, brandId, priceList, startDate, endDate, price);

        //when
        Optional<PriceResponseDto> priceDtoOptional = service.getPrice(queryDate, productId, brandId);

        //then
        assertTrue(priceDtoOptional.isPresent());
        assertEquals(priceDtoOptional.get().getPrice(), price);
    }

    @Test
    void getPriceBDDStyle() {
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
        List<PriceEntity> priceEntityList = new ArrayList<>();
        priceEntityList.add(priceEntity);
        PriceResponseDto priceResponseDto = new PriceResponseDto(id, productId, brandId, priceList, startDate, endDate, price);

        //when
        Optional<PriceResponseDto> priceDtoOptional = service.getPrice(queryDate, productId, brandId);

        //then
        assertTrue(priceDtoOptional.isPresent());
        assertEquals(priceDtoOptional.get().getPrice(), price);
    }

}