package com.ecommerce.blockchain.domain.nft;

import com.ecommerce.blockchain.domain.BaseTime;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class NFT extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private User seller;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
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

    public void setUser(User seller) {
        this.seller = seller;
    }

    @Builder
    public NFT(String imageUrl, String title, String category, String explanation){
        this.imageUrl = imageUrl;
        this.title = title;
        this.category = category;
        this.explanation = explanation;
    }
}
