package com.ecommerce.blockchain.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Purchase {

    private long id;
    private long purchaseId; // purchase Id get from contract
    private String state = PurchaseState.I.toString(); // I(Initial-purchased), P(Paid), S(sent), C(confirmed), X(cancelled)
    private long sellerId;
    private long buyerId;
    private long itemId;
    private LocalDateTime createdAt;
    private String contractAddress;

    @Override
    public String toString()
    {
        return "{ id: " + id +
                "\n\tpurchaseId: " + purchaseId +
                "\n\tstate: " + state +
                "\n\tsellerId: " + sellerId +
                "\n\tbuyerId: " + buyerId +
                "\n\titemId: " + itemId +
                "\n\tcreatedAt: " + createdAt +
                "\n\tcontractAddress: " + contractAddress +
                " }";
    }
}
