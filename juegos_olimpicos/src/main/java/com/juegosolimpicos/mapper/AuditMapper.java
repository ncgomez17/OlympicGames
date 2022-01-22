package com.juegosolimpicos.mapper;

import com.juegosolimpicos.model.entities.mongo.AuditDocumentEntity;
import com.juegosolimpicos.openapi.model.AuditDto;
import com.juegosolimpicos.openapi.model.FindAuditResponseDto;
import com.juegosolimpicos.openapi.model.PagedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Objects;

@Mapper(componentModel = "spring",mappingControl = DeepClone.class,uses={ObjectIdMapper.class,DateMapper.class})
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    AuditDocumentEntity clone(AuditDocumentEntity in);

    @Mapping(target = "_id", source = "objectId")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entity", source = "entity")
    @Mapping(target = "action", source = "action")
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate")
    @Mapping(target = "previous", source = "previous")
    AuditDocumentEntity toAuditEntity(AuditDto dto);

    @Mapping(target = "objectId", source = "_id")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entity", source = "entity")
    @Mapping(target = "action", source = "action")
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate")
    @Mapping(target = "previous", source = "previous")
    AuditDto toAuditDto(AuditDocumentEntity entity);

    default FindAuditResponseDto toFindAuditResponseDto(Page<AuditDocumentEntity> page){
        if (Objects.isNull(page)) {
            return null;
        }
        FindAuditResponseDto findAuditResponseDto = new FindAuditResponseDto();
        PagedDto pagedDto = new PagedDto();
        pagedDto.setClassName("AuditDto");
        pagedDto.setEmpty(page.isEmpty());
        pagedDto.setFirst(page.isFirst());
        pagedDto.setLast(page.isLast());
        pagedDto.setOffset(page.getNumber());
        pagedDto.setLimit(page.getSize());
        pagedDto.setNumberOfElements(page.getNumberOfElements());
        pagedDto.setTotalPages(page.getTotalPages());
        pagedDto.setTotalElements(page.getTotalElements());

        findAuditResponseDto.setAudits(page.map(this::toAuditDto).getContent());
        findAuditResponseDto.setPagination(pagedDto);

        return findAuditResponseDto;

    }
}
