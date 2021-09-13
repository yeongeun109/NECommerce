package com.ecommerce.blockchain.domain;

import lombok.Data;
import org.springframework.util.Assert;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private long id;
    private String hash;
    private String nonce;
    private String blockHash;
    private String blockNumber;
    private String transactionIndex;
    private String from;
    private String to;
    private String value;
    private String gasPrice;
    private String gas;
    private String input;
    private String creates;
    private String publicKey;
    private String raw;
    private String r;
    private String s;
    private int v;
    private LocalDateTime storedAt;

    public Transaction() { }

    public Transaction(final EthBlock.TransactionResult txResult)
    {
        Assert.isTrue(txResult instanceof EthBlock.TransactionObject, "Wrong EthBlock.TransactionResult instance type");

        org.web3j.protocol.core.methods.response.Transaction tx = ((EthBlock.TransactionObject) txResult).get();
        this.hash = tx.getHash();
        this.nonce = String.valueOf(tx.getNonce());
        this.blockHash = tx.getBlockHash();
        this.blockNumber = String.valueOf(tx.getBlockNumber());
        this.transactionIndex = String.valueOf(tx.getTransactionIndex());
        this.from = tx.getFrom();
        this.to = tx.getTo();
        this.value = String.valueOf(tx.getValue());
        this.gasPrice = String.valueOf(tx.getGasPrice());
        this.gas = String.valueOf(tx.getGas());
        if(tx.getInput().length() < 300)
            this.input = tx.getInput();
        this.creates = tx.getCreates();
        this.publicKey = tx.getPublicKey();
        this.raw = tx.getRaw();
        this.r = tx.getR();
        this.s = tx.getS();
        this.v = (int) tx.getV();
        this.storedAt = LocalDateTime.now();
    }
}
