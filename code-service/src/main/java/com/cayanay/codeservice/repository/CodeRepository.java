package com.cayanay.codeservice.repository;

import com.cayanay.codeservice.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Code getCodeByCode(String code);
}
