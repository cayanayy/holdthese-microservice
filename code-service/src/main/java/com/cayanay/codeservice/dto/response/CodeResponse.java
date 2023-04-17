package com.cayanay.codeservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponse {
    private Long codeId;
    private String code;
    private LocalDateTime expiresAt;
}
