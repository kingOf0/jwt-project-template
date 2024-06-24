package com.kingof0.jwtprojecttemplate.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kingof0.jwtprojecttemplate.config.FileStorageProperties;
import com.kingof0.jwtprojecttemplate.exception.BadRequestException;
import com.kingof0.jwtprojecttemplate.exception.StorageException;
import com.kingof0.jwtprojecttemplate.model.dto.file.UnsavedFile;
import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import com.kingof0.jwtprojecttemplate.repository.FileStorageRepository;
import com.kingof0.jwtprojecttemplate.util.Utils;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final FileStorageRepository fileStorageRepository;
    private final Path rootLocation;

    @Value("${file.secret-key}")
    String secretKey;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.rootLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.rootLocation);
        } catch (Exception ex) {
            throw new BadRequestException("Yüklenilecek dosyaların saklanılacağı dizin oluşturulamadı.");
        }
    }

    public void deleteAllFiles() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void deleteUploadedFile(UploadedFile uploadedFile) {
        Path destinationFile = this.rootLocation.resolve(Paths.get(uploadedFile.getPath(), uploadedFile.getFileName()))
                .normalize().toAbsolutePath();
        try {
            Files.deleteIfExists(destinationFile);
        } catch (IOException e) {
            throw new StorageException("Dosya silinemedi.", e);
        }
    }

    public UploadedFile store(@NotNull UnsavedFile unsavedFile) {
        MultipartFile file = unsavedFile.getFile();

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(Utils.generateFileName(file));
        uploadedFile.setContentType(Utils.getContentType(file));
        uploadedFile.setOriginalFileName(file.getOriginalFilename());
        uploadedFile.setPath(unsavedFile.getPath());
        uploadedFile.setTitle(unsavedFile.getTitle());
        uploadedFile.setUrl("/api/file/" + uploadedFile.getPath() + "/" + uploadedFile.getId());
        uploadedFile.setExtension(Utils.getExtension(file));

        try {
            if (file.isEmpty()) {
                throw new StorageException("Boş dosya kaydedilemedi.");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(uploadedFile.getPath(), uploadedFile.getFileName()))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().startsWith(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Mevcut dizin dışına dosya kaydedilemez.");
            }

            if (!destinationFile.toFile().mkdirs()) {
                throw new StorageException("Dizin oluşturulamadı.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileStorageRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new StorageException("Dosya Kaydedilemedi.", e);
        }
    }

    public Resource loadAsResource(UploadedFile uploadedFile) {
        return Utils.loadAsResource(this.rootLocation.resolve(uploadedFile.getPath()), uploadedFile.getFileName());
    }

    public Stream<Path> loadAllResources() {
        return Utils.loadAll(this.rootLocation);
    }

    public List<UploadedFile> getAllData() {
        return fileStorageRepository.findAll();
    }

    public UploadedFile getDataById(Long id) {
        return fileStorageRepository.findById(id).orElse(null);
    }

    public Resource getResourceById(Long id) {
        UploadedFile uploadedFile = getDataById(id);
        if (uploadedFile == null)
            return null;
        return loadAsResource(uploadedFile);
    }

    public List<UploadedFile> getAllDataByPath(String path) {
        return fileStorageRepository.findAllByPath(path);
    }

    @Transactional
    public void delete(Long id) {
        UploadedFile uploadedFile = getDataById(id);
        if (uploadedFile == null)
            return;
        deleteUploadedFile(uploadedFile);

        fileStorageRepository.deleteById(id);
    }

    public String generateToken(UploadedFile file, long validity) {
        Date now = new Date();
        Date valid = new Date(now.getTime() + validity);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withIssuer(file.getId().toString())
                .withIssuedAt(now)
                .withExpiresAt(valid)
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        } catch (Exception ignored) {
        }
        return null;
    }
}
