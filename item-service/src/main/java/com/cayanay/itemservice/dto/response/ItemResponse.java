package com.cayanay.itemservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private long id;
    private String message;
    private LocalDateTime unableAfter;
}
