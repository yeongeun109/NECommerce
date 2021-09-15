package com.ecommerce.blockchain.domain.wallet;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private int walletId;


}
