package com.ecommerce.blockchain.domain.product;

import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(nullable = false, precision = 18, scale = 13)
    private BigDecimal price;

    @Builder
    public Product(User user, NFT nft, BigDecimal price) {
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
