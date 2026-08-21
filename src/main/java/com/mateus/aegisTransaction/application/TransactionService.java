package com.mateus.aegisTransaction.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mateus.aegisTransaction.domain.Transaction;
import com.mateus.aegisTransaction.infrastructure.TransactionRepository;
import com.mateus.aegisTransaction.presentation.dto.UpdateTransactionDto;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String id) {
        return transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with id " + id + " not found."));
    }

    public List<Transaction> listTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(String id, UpdateTransactionDto updatedTransaction) throws IllegalArgumentException {
        Transaction existingTransaction = transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with id " + id + " not found."));
        existingTransaction.setDescription(updatedTransaction.getDescription());
        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }

}
