package com.kingof0.jwtprojecttemplate.repository;

import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileStorageRepository extends JpaRepository<UploadedFile, Long> {
    List<UploadedFile> findAllByPath(String path);
}
