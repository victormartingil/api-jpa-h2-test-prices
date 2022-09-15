package com.plexus.prices.service;

import com.plexus.prices.models.dto.PriceDto;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Interface for Price Service
 *
 * @author victor.martingil
 */
public interface PriceService {

    /**
     * @param date
     * @param productId
     * @param brandId
     * @return
     */
    Optional<PriceDto> getPrice(LocalDateTime date, Integer productId, Integer brandId);
}
