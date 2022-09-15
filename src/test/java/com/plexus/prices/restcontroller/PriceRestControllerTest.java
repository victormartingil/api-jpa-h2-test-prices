package com.plexus.prices.restcontroller;

import com.plexus.prices.models.dto.PriceDto;
import com.plexus.prices.models.dto.PriceQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author victor.martingil
 **/
@SpringBootTest
@AutoConfigureMockMvc
class PriceRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    PriceQuery priceQuery1;
    PriceQuery priceQuery2;
    PriceQuery priceQuery3;
    PriceQuery priceQuery4;
    PriceQuery priceQuery5;
    PriceDto priceDto;

    private static final String GET_PRICES_URL = "/api/prices";

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @BeforeEach
    void setUp() {
        priceQuery1 = PriceQuery.builder()
                .date(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                .productId(35455)
                .brandId(1).build();
        priceQuery2 = PriceQuery.builder()
                .date(LocalDateTime.of(2020, 6, 14, 16, 0, 0))
                .productId(35455)
                .brandId(1).build();
        priceQuery3 = PriceQuery.builder()
                .date(LocalDateTime.of(2020, 6, 14, 21, 0, 0))
                .productId(35455)
                .brandId(1).build();
        priceQuery4 = PriceQuery.builder()
                .date(LocalDateTime.of(2020, 6, 15, 10, 0, 0))
                .productId(35455)
                .brandId(1).build();
        priceQuery5 = PriceQuery.builder()
                .date(LocalDateTime.of(2020, 6, 16, 21, 0, 0))
                .productId(35455)
                .brandId(1).build();
        priceDto = PriceDto.builder()
                .id(1L)
                .productId(1)
                .brandId(1)
                .priceList(2)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 16, 18, 30, 0))
                .price(25.45).build();
    }

    @Test
    void getPriceTests() throws Exception {
        // Test1 -> should find priceList = 1
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery1.getDate().format(dtf)).queryParam("productId", String.valueOf(priceQuery1.getProductId())).queryParam("brandId", String.valueOf(priceQuery1.getBrandId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList", is(1)));

        // Test2 -> should find priceList = 2
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery2.getDate().format(dtf)).queryParam("productId", String.valueOf(priceQuery2.getProductId())).queryParam("brandId", String.valueOf(priceQuery2.getBrandId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList", is(2)));

        // Test3 -> should find priceList = 1
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery3.getDate().format(dtf)).queryParam("productId", String.valueOf(priceQuery3.getProductId())).queryParam("brandId", String.valueOf(priceQuery3.getBrandId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList", is(1)));

        // Test4 -> should find priceList = 3
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery4.getDate().format(dtf)).queryParam("productId", String.valueOf(priceQuery4.getProductId())).queryParam("brandId", String.valueOf(priceQuery4.getBrandId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList", is(3)));

        // Test5 -> should find priceList = 4
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery5.getDate().format(dtf)).queryParam("productId", String.valueOf(priceQuery5.getProductId())).queryParam("brandId", String.valueOf(priceQuery5.getBrandId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList", is(4)));
    }

    @Test
    void getPriceTestNotOk() throws Exception {
        mockMvc.perform(get(GET_PRICES_URL).contentType(MediaType.APPLICATION_JSON).queryParam("date", priceQuery1.getDate().format(dtf)).queryParam("productId", String.valueOf(0)).queryParam("brandId", String.valueOf(0)))
                .andExpect(status().isNotFound());
    }
}