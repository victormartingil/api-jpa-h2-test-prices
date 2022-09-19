package com.plexus.prices.restcontroller;


import com.plexus.prices.models.dto.PriceDto;
import com.plexus.prices.service.impl.PriceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Prices Controller", description = "This is the prices controller")
public class PriceRestController {

    private PriceServiceImpl priceService;

    @Autowired
    public PriceRestController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "This return the Price according to request params", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping(produces = "application/json")
    public ResponseEntity<PriceDto> getPrice(@Parameter(description = "The date for the price") @RequestParam String date,
                                             @Parameter(description = "The product Id for the price") @RequestParam int productId,
                                             @Parameter(description = "The brand Id for the price") @RequestParam int brandId) {

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
