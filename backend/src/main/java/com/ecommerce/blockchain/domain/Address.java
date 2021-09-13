package com.ecommerce.blockchain.domain;

import com.ecommerce.blockchain.domain.wrapper.EthereumTransaction;

import java.math.BigInteger;
import java.util.List;

/**
 * Sub PJT Ⅲ 추가과제 관련 클래스
 */
public class Address {
    private String id;
    private BigInteger balance;
    private BigInteger txCount;
    private List<EthereumTransaction> trans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public List<EthereumTransaction> getTrans() {
        return trans;
    }

    public void setTrans(List<EthereumTransaction> trans) {
        this.trans = trans;
    }

    public BigInteger getTxCount()
    {
        return txCount;
    }

    public void setTxCount(final BigInteger txCount)
    {
        this.txCount = txCount;
    }
}
