package com.ecommerce.blockchain.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequestDto {
    private Long userId;
    private Long nftId;
    private double price;

    public Product toEntity() {
        return Product.builder().price(price).build();
    }
}
