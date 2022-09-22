package com.plexus.prices.service.impl;

import com.plexus.prices.mapper.PriceMapper;
import com.plexus.prices.models.repository.PriceRepository;
import com.plexus.prices.models.dto.PriceResponseDto;
import com.plexus.prices.models.entity.PriceEntity;
import com.plexus.prices.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Price Service Implementation
 *
 * @author victor.martingil
 */
@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;
    private PriceMapper priceMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository,
                            PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    /**
     * Get price with required params
     *
     * @param date
     * @param productId
     * @param brandId
     * @return Optional of PriceDto
     */
    @Override
    public Optional<PriceResponseDto> getPrice(LocalDateTime date, Integer productId, Integer brandId) {
        List<PriceEntity> priceEntityList = priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThan(productId, brandId, date, date);
        if (priceEntityList.isEmpty()) return Optional.empty();

        PriceEntity priceEntity = priceEntityList.stream().max(Comparator.comparing(PriceEntity::getPriority)).orElse(null);

        PriceResponseDto priceResponseDto = priceMapper.convertEntityToResponseDto(priceEntity);

        return Optional.of(priceResponseDto);
    }

}
