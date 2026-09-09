package com.mateus.aegisTransaction.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionValidationDto(String status, String message, String transactionId) {

    public static TransactionValidationDto fromJson(String message) {
        try {
            return new ObjectMapper().readValue(message, TransactionValidationDto.class);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Invalid transaction validation message", exception);
        }
    }
}
