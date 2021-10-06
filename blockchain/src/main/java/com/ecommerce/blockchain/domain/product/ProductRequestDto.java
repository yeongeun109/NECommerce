package com.ecommerce.blockchain.domain.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter @Setter
public class ProductRequestDto {
    private Long userId;
    private Long nftId;
    private Long price;

    public Product toEntity() {
        return Product.builder().price(price).build();
    }
}
