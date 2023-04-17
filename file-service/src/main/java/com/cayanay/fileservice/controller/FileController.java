package com.cayanay.fileservice.controller;

import com.cayanay.fileservice.dto.request.CreateFileRequest;
import com.cayanay.fileservice.dto.response.FileResponse;
import com.cayanay.fileservice.service.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FileResponse> getFilesByItem(@Valid @RequestParam("itemId") Long itemId) {
        return fileService.getFilesByItem(itemId);
    }

    @GetMapping("/{fileCode}")
    @ResponseStatus(HttpStatus.OK)
    public FileResponse getFile(@Valid @PathVariable("fileCode") String fileCode) {
        return fileService.getFile(fileCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFile(@Valid @ModelAttribute CreateFileRequest createFileRequest) {
        fileService.createFile(createFileRequest);
    }
}
