package com.cayanay.codeservice.service;

import com.cayanay.codeservice.dto.request.CreateCodeRequest;
import com.cayanay.codeservice.dto.response.CodeResponse;
import com.cayanay.codeservice.core.mapper.ModelMapperService;
import com.cayanay.codeservice.entity.Code;
import com.cayanay.codeservice.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeService {
    private final CodeRepository codeRepository;
    private final ModelMapperService modelMapperService;

    public List<CodeResponse> getCodes() {
        List<Code> codes = codeRepository.findAll();
        log.info("access successful to all codes");
        return codes.stream().map(code -> modelMapperService.forResponse().map(code, CodeResponse.class)).toList();
    }

    public CodeResponse getCode(String code) {
        Code foundCode = codeRepository.getCodeByCode(code);
        log.info("accessed to {}", foundCode.getCode());
        return modelMapperService.forResponse().map(foundCode, CodeResponse.class);
    }

    public void createCode(CreateCodeRequest createCodeRequest) {
        Code code = modelMapperService.forRequest().map(createCodeRequest, Code.class);
        code.setExpiresAt(LocalDateTime.now().plusMinutes(createCodeRequest.getDuration()));
        log.info("code created {}", code.getId());
        codeRepository.save(code);
    }
}
