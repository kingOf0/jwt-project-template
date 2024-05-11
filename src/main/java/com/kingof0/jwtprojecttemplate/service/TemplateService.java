package com.kingof0.jwtprojecttemplate.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kingof0.jwtprojecttemplate.exception.FileServeException;
import com.kingof0.jwtprojecttemplate.model.dto.file.ServedFile;
import com.kingof0.jwtprojecttemplate.model.dto.file.TemplateModel;
import com.kingof0.jwtprojecttemplate.model.dto.file.UnsavedFile;
import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final FileStorageService fileStorageService;
    private final String path = "templates";
    private final Long validity = Duration.ofMinutes(10).toMillis();

    public List<ServedFile> getAllTemplates() {
        return fileStorageService.getAllDataByPath(path).stream().map(this::getServedFile).toList();
    }

    private ServedFile getServedFile(UploadedFile uploadedFile) {
        ServedFile servedFile = new ServedFile();
        servedFile.setUploadedFile(uploadedFile);
        servedFile.setToken(fileStorageService.generateToken(uploadedFile, validity));
        return servedFile;
    }

    public Resource getTemplateById(Long id) {
        return fileStorageService.getResourceById(id);
    }

    public UploadedFile store(TemplateModel model) {
        UnsavedFile unsavedFile = new UnsavedFile();
        unsavedFile.setFile(model.getFile());
        unsavedFile.setTitle(model.getTitle());
        unsavedFile.setPath(path);

        return fileStorageService.store(unsavedFile);
    }

    @Transactional
    public void delete(Long id) {
        fileStorageService.delete(id);
    }

    public Resource getTemplateById(Long id, String token) {
        try {
            DecodedJWT decodedJWT = fileStorageService.decodeToken(token);
            if (decodedJWT == null) {
                throw new FileServeException("Token deşifre edilemedi!");
            }
            if (!decodedJWT.getIssuer().equals(id.toString())) {
                throw new FileServeException("Token geçerli ama bu dosya için değil!");
            }
            return fileStorageService.getResourceById(id);
        } catch (Exception e) {
            throw new FileServeException("Token geçersiz!", e);
        }
    }
}
