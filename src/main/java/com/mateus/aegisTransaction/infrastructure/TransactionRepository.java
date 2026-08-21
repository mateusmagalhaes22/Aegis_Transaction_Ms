package com.mateus.aegisTransaction.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.aegisTransaction.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
}