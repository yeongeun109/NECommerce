package com.ecommerce.blockchain.domain.nft;

import com.ecommerce.blockchain.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NFTResponseDto {
    private Long id;
    private User owner;
    private String imageUrl;
    private String title;
    private String category;
    private String explanation;
    private String transectionHash;

    public NFTResponseDto(NFT entity) {
        this.id = entity.getId();
        this.owner = entity.getOwner();
        this.imageUrl = entity.getImageUrl();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.explanation = entity.getExplanation();
        this.transectionHash=entity.getTransectionHash();
    }

    @Override
    public String toString() {
        return "id : "+ this.id +" img : "+this.imageUrl +" title : "+ this.title + " cat : "+ this.category + " explanation : " + this.explanation;
    }
}
