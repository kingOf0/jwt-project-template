package com.kingof0.jwtprojecttemplate.model.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TemplateModel {

    private MultipartFile file;
    private String title;

}
