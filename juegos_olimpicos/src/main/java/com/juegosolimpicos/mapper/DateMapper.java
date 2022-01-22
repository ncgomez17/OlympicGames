package com.juegosolimpicos.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Mapper
public interface DateMapper {

    static Date map(final LocalDate value){
        return Objects.isNull(value) ?
                null :Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    static LocalDate map(final Date value){
        return Objects.isNull(value) ? null: value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
