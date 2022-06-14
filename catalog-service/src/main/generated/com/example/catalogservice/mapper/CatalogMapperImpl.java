package com.example.catalogservice.mapper;

import com.example.catalogservice.data.CatalogData;
import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.vo.ResponseCatalog;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-14T16:22:58+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class CatalogMapperImpl implements CatalogMapper {

    @Override
    public CatalogEntity toEntity(CatalogData catalogData) {
        if ( catalogData == null ) {
            return null;
        }

        CatalogEntity.CatalogEntityBuilder catalogEntity = CatalogEntity.builder();

        catalogEntity.productId( catalogData.getProductId() );
        catalogEntity.unitPrice( catalogData.getUnitPrice() );
        catalogEntity.createdAt( catalogData.getCreatedAt() );

        return catalogEntity.build();
    }

    @Override
    public CatalogDto toDto(CatalogData catalogData) {
        if ( catalogData == null ) {
            return null;
        }

        CatalogDto.CatalogDtoBuilder catalogDto = CatalogDto.builder();

        catalogDto.productId( catalogData.getProductId() );
        catalogDto.qty( catalogData.getQty() );
        catalogDto.unitPrice( catalogData.getUnitPrice() );
        catalogDto.totalPrice( catalogData.getTotalPrice() );
        catalogDto.orderId( catalogData.getOrderId() );
        catalogDto.userId( catalogData.getUserId() );

        return catalogDto.build();
    }

    @Override
    public ResponseCatalog toResponseCatalog(CatalogData catalogData) {
        if ( catalogData == null ) {
            return null;
        }

        ResponseCatalog.ResponseCatalogBuilder responseCatalog = ResponseCatalog.builder();

        responseCatalog.productId( catalogData.getProductId() );
        responseCatalog.unitPrice( catalogData.getUnitPrice() );
        responseCatalog.createdAt( catalogData.getCreatedAt() );

        return responseCatalog.build();
    }
}
