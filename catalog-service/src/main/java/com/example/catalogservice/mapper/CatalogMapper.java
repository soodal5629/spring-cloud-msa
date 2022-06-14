package com.example.catalogservice.mapper;

import com.example.catalogservice.data.CatalogData;
import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.vo.ResponseCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    @Mapping(target = "id", ignore = true)
    CatalogEntity toEntity(CatalogData catalogData);
    CatalogDto toDto(CatalogData catalogData);

    ResponseCatalog toResponseCatalog(CatalogData catalogData);
}
