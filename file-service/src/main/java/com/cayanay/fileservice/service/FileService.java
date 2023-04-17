package com.cayanay.fileservice.service;

import com.cayanay.fileservice.core.mapper.ModelMapperService;
import com.cayanay.fileservice.dto.request.CreateFileRequest;
import com.cayanay.fileservice.dto.response.FileResponse;
import com.cayanay.fileservice.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cayanay.fileservice.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final ModelMapperService modelMapperService;

    public FileResponse getFile(String fileCode) {
        File file = fileRepository.getFileByFileCode(fileCode);
        return modelMapperService.forResponse().map(file, FileResponse.class);
    }

    public List<FileResponse> getFilesByItem(Long itemId) {
        List<File> files = fileRepository.getFilesByItemId(itemId);
        return files.stream().map(file -> modelMapperService.forResponse().map(file, FileResponse.class)).toList();
    }

    public void createFile(CreateFileRequest createFileRequest) {
        MultipartFile uploadedFile = createFileRequest.getFile();
        String fileCode = Utils.md5Hash(uploadedFile.getName().concat(String.valueOf(System.currentTimeMillis())));
        File file = File.builder()
                .name(uploadedFile.getOriginalFilename())
                .type(uploadedFile.getContentType())
                .itemId(createFileRequest.getItemId())
                .fileCode(fileCode)
                .build();
        fileRepository.save(file);
    }
}
