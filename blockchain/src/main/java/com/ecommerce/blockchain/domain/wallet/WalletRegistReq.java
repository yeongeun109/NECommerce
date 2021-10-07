package com.ecommerce.blockchain.domain.wallet;

import lombok.Data;

@Data
public class WalletRegistReq {
    private String address;
    private Long ownerId;
}
