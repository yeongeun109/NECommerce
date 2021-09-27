package com.ecommerce.blockchain.domain.wallet;

import com.ecommerce.blockchain.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private int id;

    private String address;

    private BigDecimal balance;

    @Column(name = "receiving_count")
    private int receivingCount;

    private int cash;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // FK
    @JsonIgnore
    private User user;
}
