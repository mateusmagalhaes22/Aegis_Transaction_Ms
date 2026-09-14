package com.mateus.aegisTransaction.infrastructure.messaging;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mateus.aegisTransaction.application.TransactionService;
import com.mateus.aegisTransaction.domain.Status;
import com.mateus.aegisTransaction.presentation.dto.TransactionValidationDto;

@Component
public class ValidationKafkaListener {

    private final TransactionService transactionService;

    public ValidationKafkaListener(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "${app.kafka.transaction-validation-topic:transaction-validation}")
    public void consume(String message) {
        try {
            TransactionValidationDto validationDto = TransactionValidationDto.fromJson(message);
            Status status = "SUCCESS".equalsIgnoreCase(validationDto.status())
                    ? Status.APPROVED
                    : Status.REJECTED;
            transactionService.validateTransaction(validationDto.transactionId(), status);
        } catch (IllegalArgumentException exception) {
            System.err.println("Failed to process transaction validation message: " + exception.getMessage());
        }
    }
}
