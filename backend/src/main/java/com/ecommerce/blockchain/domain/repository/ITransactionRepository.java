package com.ecommerce.blockchain.domain.repository;

import com.ecommerce.blockchain.domain.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> list();
    Transaction get(String hash);
    List<Transaction> getByAddress(String address);
    long add(Transaction tx);
}
