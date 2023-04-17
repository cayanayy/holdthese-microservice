package com.cayanay.codeservice.controller;

import com.cayanay.codeservice.dto.request.CreateCodeRequest;
import com.cayanay.codeservice.dto.response.CodeResponse;
import com.cayanay.codeservice.service.CodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/codes")
@RequiredArgsConstructor
public class CodeController {
    private final CodeService codeService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CodeResponse> getCodes() {
        return codeService.getCodes();
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse getCode(@Valid @PathVariable("code") String code) {
        return codeService.getCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCode(@Valid @RequestBody CreateCodeRequest createCodeRequest) {
        codeService.createCode(createCodeRequest);
    }
}
