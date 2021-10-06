package com.ecommerce.blockchain.domain.product;

import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = NFT.class, fetch = FetchType.EAGER)
    @JoinColumn(name="nft_id")
    private NFT nft;

    @Column(nullable = false)
    private boolean status = Boolean.TRUE;

    @Column(nullable = false)
    private Long price;

    @Builder
    public Product(User user, NFT nft, Long price) {
        this.user = user;
        this.nft = nft;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", user=" + user +
                ", nft=" + nft +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
