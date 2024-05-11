package com.kingof0.jwtprojecttemplate.util;

import com.kingof0.jwtprojecttemplate.exception.StorageException;
import com.kingof0.jwtprojecttemplate.exception.StorageFileNotFoundException;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

public class Utils {


    public static Stream<Path> loadAll(Path rootLocation) {
        try {
            return Files.walk(rootLocation, 1).filter(path -> !path.equals(rootLocation)).map(rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    public static Resource loadAsResource(Path rootLocation, String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public static String generateFileName(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        if (file.getOriginalFilename() == null) {
            return fileName;
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return fileName + extension;
    }

    public static String getContentType(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to get content type.");
        }
        Tika tika = new Tika();
        try {
            return tika.detect(file.getBytes());
        } catch (IOException e) {
            throw new StorageException("Failed to get content type.", e);
        }
    }

    public static String getExtension(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to get extension.");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new StorageException("Failed to get extension.");
        }
        return originalFilename.substring(file.getOriginalFilename().lastIndexOf("."));
    }

}
