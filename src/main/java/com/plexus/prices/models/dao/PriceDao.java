package com.plexus.prices.models.dao;

import com.plexus.prices.models.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JpaRepository interface for Price
 *
 * @author victor.martingil
 */
public interface PriceDao extends JpaRepository<PriceEntity, Long> {

    /**
     *
     * @param productId
     * @param brandId
     * @param startDate
     * @param endDate
     * @return List of PriceEntity
     */
    List<PriceEntity> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThan(Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);

}
