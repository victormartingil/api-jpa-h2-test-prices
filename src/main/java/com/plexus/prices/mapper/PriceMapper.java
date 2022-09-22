package com.plexus.prices.mapper;

import com.plexus.prices.models.dto.PriceResponseDto;
import com.plexus.prices.models.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author victor.martingil
 **/
@Mapper
public interface PriceMapper {

    PriceResponseDto convertEntityToResponseDto(PriceEntity priceEntity);

    List<PriceResponseDto> convertEntityToResponseDto(List<PriceEntity> priceEntityList);

}
