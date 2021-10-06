package com.ecommerce.blockchain.domain.product;

import lombok.Data;

@Data
public class ProductPurchaseRequestDto {

    private Long buyerId;
    private Long nftId;

}
