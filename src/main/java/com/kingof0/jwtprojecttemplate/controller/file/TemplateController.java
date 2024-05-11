package com.kingof0.jwtprojecttemplate.controller.file;


import com.kingof0.jwtprojecttemplate.model.dto.file.ServedFile;
import com.kingof0.jwtprojecttemplate.model.dto.file.TemplateModel;
import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import com.kingof0.jwtprojecttemplate.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_TEMPLATE') or hasAuthority('ADMINISTRATOR')")
    public List<ServedFile> listUploadedFiles() {
        return templateService.getAllTemplates();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @RequestParam String token) {
        Resource file = templateService.getTemplateById(id, token);
        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('UPLOAD_TEMPLATE') or hasAuthority('ADMINISTRATOR')")
    public UploadedFile handleFileUpload(@ModelAttribute TemplateModel model) {
        return templateService.store(model);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_TEMPLATE') or hasAuthority('ADMINISTRATOR')")
    public void deleteFile(@PathVariable Long id) {
        templateService.delete(id);
    }



}
