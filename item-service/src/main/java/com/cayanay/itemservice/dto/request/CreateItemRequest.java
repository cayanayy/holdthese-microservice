package com.cayanay.itemservice.dto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {
    private String message;
    private String codeId;
    private Long duration;
    @Nullable
    private List<MultipartFile> files;
}
