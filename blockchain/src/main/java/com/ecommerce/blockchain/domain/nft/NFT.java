package com.ecommerce.blockchain.domain.nft;

import com.ecommerce.blockchain.domain.BaseTime;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class NFT extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(name="seller_id")
//    private User seller;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private User owner;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String explanation;

    @Column(nullable = false)
    private String transactionHash;

    @Column(nullable = false)
    private Long tokenId;

    public void setUser(User owner) {
        this.owner = owner;
    }

    @Builder
    public NFT(String imageUrl, String title, String category, String explanation, String transactionHash, Long tokenId){
        this.imageUrl = imageUrl;
        this.title = title;
        this.category = category;
        this.explanation = explanation;
        this.transactionHash = transactionHash;
        this.tokenId = tokenId;
    }
}
