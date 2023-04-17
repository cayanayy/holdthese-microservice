package com.cayanay.fileservice.repository;

import com.cayanay.fileservice.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    File getFileByFileCode(String fileCode);

    List<File> getFilesByItemId(Long itemId);
}
