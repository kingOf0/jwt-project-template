package com.kingof0.jwtprojecttemplate.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String title;

    /**
     * This is the name of the file on the server.
     * It is generated by the server.
     */
    private String fileName;

    private String contentType;
    private String extension;

    @Column(nullable = true)
    private String originalFileName;

}
