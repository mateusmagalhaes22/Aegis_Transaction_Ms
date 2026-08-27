package com.mateus.aegisTransaction.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String userId;
    private String description;
    private double amount;
    private Date date;

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
