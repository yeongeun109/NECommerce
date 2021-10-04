package com.ecommerce.blockchain.domain.nft;

import com.ecommerce.blockchain.domain.BaseTime;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class NFT extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private User user;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String explanation;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public NFT(User user, String imageUrl, String title, String category, String explanation){
        this.user = user;
        this.imageUrl = imageUrl;
        this.title = title;
        this.category = category;
        this.explanation = explanation;
    }
}
