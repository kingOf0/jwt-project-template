package com.kingof0.jwtprojecttemplate.model.dto.file;

import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServedFile {

    private UploadedFile uploadedFile;
    private String token;

}
