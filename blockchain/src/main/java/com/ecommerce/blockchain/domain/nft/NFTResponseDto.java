package com.ecommerce.blockchain.domain.nft;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NFTResponseDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String category;
    private String explanation;

    public NFTResponseDto(NFT entity) {
        this.id = entity.getId();
        this.imageUrl = entity.getImageUrl();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.explanation = entity.getExplanation();
    }

    @Override
    public String toString() {
        return "id : "+ this.id +" img : "+this.imageUrl +" title : "+ this.title + " cat : "+ this.category + " explanation : " + this.explanation;
    }
}
