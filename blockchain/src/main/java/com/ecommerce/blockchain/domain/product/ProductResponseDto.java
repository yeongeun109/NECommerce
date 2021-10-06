package com.ecommerce.blockchain.domain.product;

import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponseDto {

    Long id;
    private User user;
    private NFT nft;
    private boolean status;
    private Long price;

    public ProductResponseDto(Product entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.nft = entity.getNft();
        this.status = entity.isStatus();
        this.price = entity.getPrice();
    }

}
