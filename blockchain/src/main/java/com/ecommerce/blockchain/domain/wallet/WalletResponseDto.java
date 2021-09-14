package com.ecommerce.blockchain.domain.wallet;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResponseDto {
    private String address;
    private BigDecimal balance;
    private int id;
    private int ownerId;
    private int receivingCount;
}
