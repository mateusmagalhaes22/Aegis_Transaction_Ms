package com.mateus.aegisTransaction.presentation.dto;

import lombok.Data;

@Data
public class UpdateTransactionDto {

    private String description;

    public String getDescription() {
        return description;
    }

}