package com.ecommerce.blockchain.domain.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ProductRequestDto {
    private Long userId;
    private Long nftId;
    private BigDecimal price;

    public Product toEntity() {
        return Product.builder().price(price).build();
    }
}
