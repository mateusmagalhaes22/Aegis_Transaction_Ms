package com.mateus.aegisTransaction.application;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mateus.aegisTransaction.domain.Status;
import com.mateus.aegisTransaction.domain.Transaction;
import com.mateus.aegisTransaction.infrastructure.messaging.TransactionRepository;
import com.mateus.aegisTransaction.presentation.dto.CreateTransactionDto;
import com.mateus.aegisTransaction.presentation.dto.UpdateTransactionDto;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final String transactionsTopic;

    public TransactionService(
            TransactionRepository transactionRepository,
            KafkaTemplate<String, Transaction> kafkaTemplate,
            @Value("${app.kafka.transactions-topic:transactions}") String transactionsTopic) {
        this.transactionRepository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.transactionsTopic = transactionsTopic;
    }

    public Transaction createTransaction(CreateTransactionDto transactionDto) {
        Date currentDate = new Date();
        Transaction transaction = new Transaction();
        transaction.setUserId(transactionDto.userId());
        transaction.setDescription(transactionDto.description());
        transaction.setAmount(transactionDto.amount());
        transaction.setDate(currentDate);
        transaction.setStatus(Status.PENDING);
        Transaction savedTransaction = transactionRepository.save(transaction);
        kafkaTemplate.send(transactionsTopic, savedTransaction.getId(), savedTransaction);
        return savedTransaction;
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

    public void validateTransaction(String id, Status newStatus) {
        Transaction transaction = this.getTransaction(id);
        transaction.setStatus(newStatus);
        transactionRepository.save(transaction);
    }

}
