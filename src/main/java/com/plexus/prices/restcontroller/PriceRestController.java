package com.plexus.prices.restcontroller;


import com.plexus.prices.models.dto.PriceDto;
import com.plexus.prices.service.impl.PriceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * RestController for Price Entity
 *
 * @author victor.martingil
 */
@RestController
@Slf4j
@RequestMapping("/api/prices")
public class PriceRestController {

    private PriceServiceImpl priceService;

    @Autowired
    public PriceRestController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @GetMapping (produces = "application/json")
    public ResponseEntity<PriceDto> getPrice(@RequestParam String date, @RequestParam int productId, @RequestParam int brandId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        log.info("Getting price with start date: {}, product Id:{}, brand Id:{}.", localDateTime, productId, brandId);

        Optional<PriceDto> priceDto = priceService.getPrice(localDateTime, productId, brandId);

        if (priceDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(priceDto.get());
        }

    }

}
