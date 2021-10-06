package com.ecommerce.blockchain.domain.nft;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NFTRequestDto {
    private Long owner_id;
    private String imageUrl;
    private String title;
    private String category;
    private String explanation;
    private String transactionHash;
    private String token;

    public NFT toEntity() {
        return NFT.builder().title(title).imageUrl(imageUrl).category(category).explanation(explanation).transactionHash(transactionHash).build();
    }
}
