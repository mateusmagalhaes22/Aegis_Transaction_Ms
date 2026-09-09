package com.mateus.aegisTransaction.presentation.dto;

public record CreateTransactionDto(
    String description,
    String userId,
    double amount
) {}
