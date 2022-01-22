package com.juegosolimpicos.services.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class PageableUtils {

    public static Pageable  createPageable(Integer offSet, Integer limitParam,
                            String sortDirection,String sortProperty){
        Integer offsetOptional = Optional.ofNullable(offSet).orElse(0);
        Integer limitOptional = Optional.ofNullable(limitParam).orElse(Integer.MAX_VALUE);
        String directionOptional = Optional.ofNullable(sortDirection).orElse("asc");
        Sort sort = Sort.by(Sort.Direction.fromString(directionOptional), sortProperty);
        Pageable pageable = PageRequest.of(offsetOptional, limitOptional, sort);

        return pageable;
    }
}
