package com.plexus.prices.service.impl;

import com.plexus.prices.converter.PriceConverter;
import com.plexus.prices.models.dao.PriceDao;
import com.plexus.prices.models.dto.PriceDto;
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

    private PriceDao priceDao;
    private PriceConverter priceConverter;

    @Autowired
    public PriceServiceImpl(PriceDao priceDao,
                            PriceConverter priceConverter) {
        this.priceDao = priceDao;
        this.priceConverter = priceConverter;
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
    public Optional<PriceDto> getPrice(LocalDateTime date, Integer productId, Integer brandId) {
        List<PriceEntity> priceEntityList = priceDao.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThan(productId, brandId, date, date);
        if (priceEntityList.isEmpty()) return Optional.empty();

        PriceEntity priceEntity = priceEntityList.stream().max(Comparator.comparing(PriceEntity::getPriority)).orElse(null);

        PriceDto priceDto = priceConverter.convertEntityToDto(priceEntity);

        return Optional.of(priceDto);
    }

}
